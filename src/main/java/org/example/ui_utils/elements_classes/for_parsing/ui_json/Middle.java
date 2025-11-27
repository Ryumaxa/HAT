package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Middle {
    private String type;
    private Limit limit; // Мб int
    private Action action;
    private String feature;
    private int position;

    @Override
    public String toString() {
        return "Middle{" +
                "type='" + type + '\'' +
                ", limit=" + limit +
                ", action=" + action +
                ", feature='" + feature + '\'' +
                ", position=" + position +
                '}';
    }
}
