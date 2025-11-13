package org.example.ui_utils.elements_classes.for_parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Layout {
    private Top[] top;
    private Middle[] mid;
    private Bottom[] bottom;

    @Override
    public String toString() {
        return "Layout{" +
                "top=" + Arrays.toString(top) +
                ", mid=" + Arrays.toString(mid) +
                ", bottom=" + Arrays.toString(bottom) +
                '}';
    }
}
