package org.example.ui_utils.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {
    public List<Integer> program;
    public Integer amount;
}
