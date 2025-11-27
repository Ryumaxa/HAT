package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private String type;
    private HashMap<String, String> title;
    private Action action;

    @Override
    public String toString() {
        return "Music{" +
                "type='" + type + '\'' +
                ", title=" + title +
                ", amount=" + action +
                '}';
    }
}
