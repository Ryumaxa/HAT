package org.example.ui_utils.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {
    @JsonProperty("ru-RU")
    public String ru;
}
