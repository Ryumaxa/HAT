package org.example.ui_utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui_utils.elements_classes.for_parsing.Root;

import java.io.File;
import java.io.IOException;

public class UiParser {
    private final String path;

    public UiParser(String path) {
        this.path = path;
    }

    public void parse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Root root = objectMapper.readValue(file, Root.class);

        String buttonTitle;
        int mode;
        System.out.println(root.getLayout().getBottom().length);
        for (int i = 0; i < root.getLayout().getBottom().length; i++) {
            if (root.getLayout().getBottom()[i].getTitle() != null) {
                buttonTitle = root.getLayout().getBottom()[i].getTitle().get("en-US");
            } else {
                buttonTitle = "Auto";
            }

            if (root.getLayout().getBottom()[i].getAction() != null && root.getLayout().getBottom()[i].getAction().getProgram() != null) {
                mode = root.getLayout().getBottom()[i].getAction().getProgram()[0];
            } else {
                mode = -1;
            }

//            if (root.getLayout().getBottom()[i].getFeature != null) {
//                mode = root.getLayout().getBottom()[i].getAction().getProgram()[0];
//            } else {
//                mode = -1;
//            }



            System.out.println(i + ") " + buttonTitle + " : " + mode);
        }
        System.out.println(root);
    }
}
