package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.ui_utils.UiParser;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sandbox {
    public static void main(String[] args) throws Exception {
//        CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();

//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File("src/main/resources/jsons/ASP 100.json");
//        Map<String, Object> buttons = objectMapper.readValue(file, new TypeReference<>(){});

//        for (Map.Entry<String, Object> entry : buttons.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            System.out.println(key + " : " + value);
//        }


//        Object values = buttons.get("layout");
//        LinkedHashMap<String, Object> layouts =  (LinkedHashMap<String, Object>) values;
//        for (Map.Entry<String, Object> entry : layouts.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            if (entry.getKey().equals("top") || entry.getKey().equals("bottom") || entry.getKey().equals("middle")) {
//                System.out.println(key + " : " + value);
//            }
//        }


        UiParser uiParser = new UiParser("src/main/resources/jsons/ASP 100.json");
        uiParser.parse();


    }
}
