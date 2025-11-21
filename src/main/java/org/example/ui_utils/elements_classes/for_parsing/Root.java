package org.example.ui_utils.elements_classes.for_parsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private String name;
    private int type;
    @JsonProperty("class")
    private String clazz;
    private Layout layout;

    @Override
    public String toString() {
        return "Root{" +
                "layout=" + layout +
                '}';
    }
}
