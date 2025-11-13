package org.example.ui_utils.gpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Layout {
    public List<UIItem> top;
    public List<UIItem> middle;
    public List<UIItem> bottom;
    public List<UIItem> music;
    public List<UIItem> schedule;
    public List<UIItem> settings;
    public List<UIItem> top_info;
    public List<UIItem> middle_info;
}
