package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Top {
    private String type;
    private HashMap<String, String> title;
    private String feature;
    private int position;

    @Override
    public String toString() {
        return "Top{" +
                "type='" + type + '\'' +
                ", title=" + title +
                ", feature='" + feature + '\'' +
                ", position=" + position +
                '}';
    }
}
