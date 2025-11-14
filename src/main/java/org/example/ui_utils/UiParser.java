package org.example.ui_utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui_utils.elements_classes.BottomButton;
import org.example.ui_utils.elements_classes.for_parsing.Root;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UiParser {
    private final String path;
    private ArrayList<BottomButton> bottomButtons;

    public UiParser(String path) {
        this.path = path;
        bottomButtons = new ArrayList<>();
    }

    public void parse() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File(path);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Root root = objectMapper.readValue(file, Root.class);
//
//        for (int i = 0; i < root.getLayout().getBottom().length; i++) {
//            String type = findType(root, i);
//            String name = findName(root, i);
//            int mode = findMode(root, i);
//            System.out.println("[" + i + "] " + type + " : " + name + " : " + mode);
//        }
        // TODO: общий метод для возврата всех элементов
    }

    public ArrayList<BottomButton> parseBottomButtons() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Root root = objectMapper.readValue(file, Root.class);

        for (int i = 0; i < root.getLayout().getBottom().length; i++) {
            String type = findType(root, i);
            String name = findName(root, i).replace(" ", "_");
            int mode = findMode(root, i);
            bottomButtons.add(new BottomButton(i, type, name, mode));
//            System.out.println(bottomButtons.get(i));
        }
        return bottomButtons;
    }

    private String findType(Root root, int i) {
        return root.getLayout().getBottom()[i].getType().toUpperCase();
    }

    private String findName(Root root, int i) {
        if (root.getLayout().getBottom()[i].getTitle() != null) {
            return root.getLayout().getBottom()[i].getTitle().get("en-US").toUpperCase();
        } else if (root.getLayout().getBottom()[i].getFeature() != null) {
            return root.getLayout().getBottom()[i].getFeature().toUpperCase();
        }
        else if(root.getLayout().getBottom()[i].getAction().getProgramData() != null) {
            return root.getLayout().getBottom()[i].getAction().getProgramData().getField().toUpperCase();
        } else {
            return "AUTO";
        }
    }

    private int findMode(Root root, int i) {
        if (root.getLayout().getBottom()[i].getAction() != null &&
                root.getLayout().getBottom()[i].getAction().getProgram() != null) {
            return root.getLayout().getBottom()[i].getAction().getProgram()[0];
        }
        else if(root.getLayout().getBottom()[i].getAction() != null &&
                root.getLayout().getBottom()[i].getAction().getProgramData() != null) {
            return root.getLayout().getBottom()[i].getAction().getProgramData().getProgram()[0];
        } else {
            return -1;
        }
    }

    public List<BottomButton> getModeButtons() throws IOException {
        if (bottomButtons.isEmpty()) {
            bottomButtons = this.parseBottomButtons();
        }
        return bottomButtons.stream().filter(a -> a.getMode() != -1).toList();
    }
}
