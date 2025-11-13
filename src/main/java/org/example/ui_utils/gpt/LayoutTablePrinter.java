package org.example.ui_utils.gpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class LayoutTablePrinter {

    static Map<String, String> globalNames = new HashMap<>();
    static Map<Integer, String> programNames = new HashMap<>();
    static Map<Integer, String> melodyNames = new HashMap<>();

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("src/main/resources/jsons/ASP 100.json"));

        // наполняем глобальные кэш-словарь имён
        buildGlobalMaps(root);

        JsonNode layout = root.get("layout");

        System.out.printf("%-12s | %-6s | %-12s | %-25s | %s%n",
                "Block", "Index", "Type", "Title", "Feature/Action");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Iterator<Map.Entry<String, JsonNode>> it = layout.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> block = it.next();
            String blockName = block.getKey();
            JsonNode items = block.getValue();

            if (!items.isArray()) continue;

            int index = 0;
            for (JsonNode item : items) {

                String type = get(item, "type");
                String title = resolveTitle(item);
                String feature = resolveFeature(item);

                System.out.printf("%-12s | %-6d | %-12s | %-25s | %s%n",
                        blockName, index, type, title, feature);

                index++;
            }
        }
    }

    // -----------------------------
    // ГЛОБАЛЬНАЯ КАРТА ИМЁН
    // -----------------------------
    private static void buildGlobalMaps(JsonNode root) {

        // Все title в JSON
        findAllTitles(root);

        // Строим карту program → имя (auto / quiet / turbo)
        JsonNode voice = root.at("/params/voice/alice/features");
        if (voice.isArray()) {
            for (JsonNode v : voice) {
                if (v.has("values")) {
                    for (JsonNode e : v.get("values")) {
                        if (e.has("value")) {
                            if (e.has("action") && e.get("action").has("program")) {
                                int pr = e.get("action").get("program").get(0).asInt();
                                programNames.put(pr, e.get("value").asText());
                            }
                        }
                    }
                }
            }
        }

        // Мелодии по amount
        melodyNames.put(0, "None");
        melodyNames.put(1, "Rain");
        melodyNames.put(2, "Sea");
        melodyNames.put(3, "Forest");
        melodyNames.put(4, "Birds");
        melodyNames.put(5, "Fireplace");
    }

    private static void findAllTitles(JsonNode node) {
        if (node.isObject() || node.isArray()) {
            node.forEach(LayoutTablePrinter::findAllTitles);
        }
        if (node.isObject()) {
            if (node.has("title")) {
                JsonNode t = node.get("title");
                if (t.has("ru-RU"))
                    globalNames.put(normalize(t.get("ru-RU").asText()), t.get("ru-RU").asText());
                if (t.has("en-US"))
                    globalNames.put(normalize(t.get("en-US").asText()), t.get("en-US").asText());
            }
        }
    }

    private static String normalize(String s) {
        return s.toLowerCase().replace("_", "").replace(" ", "");
    }

    // -----------------------------
    // ВОССТАНОВЛЕНИЕ TITLE
    // -----------------------------
    private static String resolveTitle(JsonNode item) {

        // 1. title
        if (item.has("title")) {
            JsonNode t = item.get("title");
            if (t.has("ru-RU")) return t.get("ru-RU").asText();
            if (t.has("en-US")) return t.get("en-US").asText();
        }

        // 2. program button
        if (item.has("action") && item.get("action").has("program")) {
            int pr = item.get("action").get("program").get(0).asInt();
            if (programNames.containsKey(pr))
                return programNames.get(pr).substring(0,1).toUpperCase() + programNames.get(pr).substring(1);
            return "Program " + pr;
        }

        // 3. melody
        if (item.has("action") && item.get("action").has("amount")) {
            int a = item.get("action").get("amount").asInt();
            if (melodyNames.containsKey(a)) return melodyNames.get(a);
            return "Melody " + a;
        }

        // 4. feature
        if (item.has("feature"))
            return capitalize(item.get("feature").asText());

        // 5. layout link
        if (item.has("layout"))
            return capitalize(item.get("layout").asText());

        return "(no title)";
    }

    private static String resolveFeature(JsonNode item) {

        if (item.has("feature"))
            return item.get("feature").asText();

        if (item.has("action"))
            return item.get("action").toString();

        return "";
    }

    private static String get(JsonNode node, String key) {
        if (!node.has(key)) return "";
        JsonNode n = node.get(key);
        if (n.isTextual()) return n.asText();
        return n.toString();
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }
}
