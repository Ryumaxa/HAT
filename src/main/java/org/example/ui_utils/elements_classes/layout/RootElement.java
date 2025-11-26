package org.example.ui_utils.elements_classes.layout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RootElement {
    private String name;
    private int type;
    private String clazz;

    @Override
    public String toString() {
        return  type + " : " + clazz + " : " + name;
    }
}
