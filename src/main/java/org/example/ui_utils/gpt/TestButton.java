package org.example.ui_utils.gpt;

public class TestButton {
    public int index;
    public String section;
    public String name;
    public String type;
    public Integer position;
    public Integer program;
    public Integer amount;
    public String feature;

    @Override
    public String toString() {
        return index + " | " + section + " | " + name + " | pos=" + position +
                " | program=" + program + " | amount=" + amount +
                " | feature=" + feature;
    }
}