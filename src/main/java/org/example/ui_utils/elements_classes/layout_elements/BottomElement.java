package org.example.ui_utils.elements_classes.layout_elements;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BottomElement {
    private int index;
    private String type;
    private String name;
    private int mode;

    @Override
    public String toString() {
        return "[" + index + "] : " + type + " : " + name + " : " + mode;
    }
}
