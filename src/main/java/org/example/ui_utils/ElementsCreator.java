package org.example.ui_utils;

/**
 * Класс для создания нужных элементов в модели
 */
public class ElementsCreator {
    public String createButtonWithIndex (int index) {
        return "new UiSelector().resourceId(\"com.hommyn.app:id/ltFeature\").instance(" + index + ")";
    }
}
