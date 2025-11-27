package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
    private int max;
    private int min;
    private int step;
    private int round;
    @JsonProperty("default")
    private int defaultVal;
    private int roundAlt;

    @Override
    public String toString() {
        return "Limit{" +
                "max=" + max +
                ", min=" + min +
                ", step=" + step +
                ", round=" + round +
                ", defaultVal=" + defaultVal +
                ", roundAlt=" + roundAlt +
                '}';
    }
}
