package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramData {
    private String field;
    private int value;
    private Integer[] program;

    @Override
    public String toString() {
        return "ProgramData{" +
                "field='" + field + '\'' +
                ", value=" + value +
                ", program=" + Arrays.toString(program) +
                '}';
    }
}
