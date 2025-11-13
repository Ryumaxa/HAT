package org.example.ui_utils.elements_classes.for_parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private Layout layout;

    @Override
    public String toString() {
        return "Root{" +
                "layout=" + layout +
                '}';
    }
}
