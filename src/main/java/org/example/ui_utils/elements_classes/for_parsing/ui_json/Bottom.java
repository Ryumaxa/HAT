package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bottom {
    private String type;
    private HashMap<String, String> title;
    private Action action;
    private String feature;

    @Override
    public String toString() {
        return "Bottom{" +
                "type='" + type + '\'' +
                ", title=" + title +
                ", action=" + action +
                ", feature=" + feature +
                '}';
    }
}
