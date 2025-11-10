package org.example.ui_utils;

/**
 * Класс для создания нужных элементов в модели
 */
public class ElementsCreator {
    public String createButtonWithIndex (int index) {
        return "new UiSelector().resourceId(\"com.hommyn.app:id/ltFeature\").instance(" + index + ")";
    }

    public String createButtonWithText (String text) {
        return "new UiSelector().text(\"" + text + "\")";
    }

    public String createDeviceCardWithText (String text) {
        return "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));";
    }

    public String createSliderWithIndex (int index) {
        return "new UiSelector().resourceId(\"com.hommyn.app:id/arcProgress\").instance(" + index + ")";
    }
}
