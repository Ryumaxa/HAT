package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Layout {
    private Top[] top;
    private Middle[] middle;
    private Bottom[] bottom;
    private Music[] music;
    private Settings[] settings;

    @Override
    public String toString() {
        return "Layout{" +
                "top=" + Arrays.toString(top) +
                ", mid=" + Arrays.toString(middle) +
                ", bottom=" + Arrays.toString(bottom) +
                '}';
    }
}
