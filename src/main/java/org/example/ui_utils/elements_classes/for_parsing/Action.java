package org.example.ui_utils.elements_classes.for_parsing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    private Integer[] program;
    private int position;

    @Override
    public String toString() {
        return "Action{" +
                "program=" + Arrays.toString(program) +
                ", position=" + position +
                '}';
    }
}
