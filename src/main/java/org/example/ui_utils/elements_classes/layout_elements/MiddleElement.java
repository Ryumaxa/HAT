package org.example.ui_utils.elements_classes.layout_elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.ui_utils.elements_classes.for_parsing.ui_json.Limit;

@AllArgsConstructor
@Data
public class MiddleElement {
    private int index;
    private String type;
    private String name;
    private Limit limit;

    @Override
    public String toString() {
        return "[" + index + "] : " + type + " : " + name + " : " + limit;
    }
}