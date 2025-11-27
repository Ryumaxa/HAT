package org.example.ui_utils.elements_classes.layout_elements;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MusicElement {
    private int index;
    private String type;
    private String name;
    private int value;

    @Override
    public String toString() {
        return "[" + index + "] : " + type + " : " + name + " : " + value;
    }
}