package org.example.ui_utils.gpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui_utils.gpt.Root;
import org.example.ui_utils.gpt.TestButton;
import org.example.ui_utils.gpt.UIItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Root root = mapper.readValue(new File("src/main/resources/jsons/ASP 100.json"), Root.class);

        List<TestButton> buttons = new ArrayList<>();

        parseSection("bottom", root.layout.bottom, buttons);
        parseSection("music", root.layout.music, buttons);
        parseSection("schedule", root.layout.schedule, buttons);
        parseSection("settings", root.layout.settings, buttons);

        // Вывод результата
        buttons.forEach(System.out::println);
    }

    private static void parseSection(String section, List<UIItem> list, List<TestButton> out) {
        if (list == null) return;

        for (UIItem item : list) {

            // ИСПРАВЛЕННО —— берём button + switch ВСЕГДА
            if (!"button".equals(item.type) && !"switch".equals(item.type))
                continue;

            TestButton b = new TestButton();
            b.index = out.size();  // ← СКВОЗНАЯ НУМЕРАЦИЯ
            b.section = section;
            b.type = item.type;
            b.position = item.position;
            b.feature = item.feature;

            // 1) Имя из title
            if (item.title != null && item.title.ru != null) {
                b.name = item.title.ru;
            }
            // 2) Если есть program — генерируем имя
            else if (item.action != null && item.action.program != null) {
                b.name = "Program " + item.action.program.get(0);
            }
            // 3) Если есть amount — генерируем имя
            else if (item.action != null && item.action.amount != null) {
                b.name = "Melody " + item.action.amount;
            }
            // 4) fallback имя
            else {
                b.name = section + "_button_" + b.index;
            }

            // Program
            if (item.action != null && item.action.program != null && !item.action.program.isEmpty()) {
                b.program = item.action.program.get(0);
            }

            // Amount
            if (item.action != null && item.action.amount != null) {
                b.amount = item.action.amount;
            }

            out.add(b);
        }
    }
}
