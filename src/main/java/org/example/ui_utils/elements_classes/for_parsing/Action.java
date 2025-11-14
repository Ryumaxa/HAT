package org.example.ui_utils.elements_classes.for_parsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    private Integer[] program;
    private int position;
    @JsonProperty("program_data")
    private ProgramData programData;

    @Override
    public String toString() {
        return "Action{" +
                "program=" + Arrays.toString(program) +
                ", position=" + position +
                ", program_data=" + programData +
                '}';
    }
}
