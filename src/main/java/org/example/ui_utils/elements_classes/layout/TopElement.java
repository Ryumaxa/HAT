package org.example.ui_utils.elements_classes.layout;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopElement {
    private int index;
    private String type;
    private String name;

    @Override
    public String toString() {
        return "[" + index + "] : " + type + " : " + name;
    }
}
