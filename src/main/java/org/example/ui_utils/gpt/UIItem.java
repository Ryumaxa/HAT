package org.example.ui_utils.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UIItem {
    public String type;                 // button, slider, switch, info…
    public Integer position;            // position
    public Integer index;               // index (если есть)
    public Title title;                 // объект title
    public String feature;              // feature
    public Action action;               // program, amount
}
