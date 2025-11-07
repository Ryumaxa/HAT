package org.example.screen_elements.ballu_asp100;

import org.example.ui_utils.ElementsCreator;

/**
 * Экран Ballu ASP-100
 * <p>
 * Тип - 69
 */
public class BalluAsp100Screen {
    private static final ElementsCreator ELEMENTS_CREATOR = new ElementsCreator();

    public static final String DEVICE_NAME = "ASP100 TEST";
    public static final String CARD_XPATH = String.format("//*[@text='%s']", DEVICE_NAME);

    public static final String FAN_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(0);
    public static final String NIGHT_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(1);
    public static final String AUTO_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(2);
    public static final String TURBO_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(3);
    public static final String SCHEDULES_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(4);
    public static final String SOUNDS_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(5);
    public static final String POWER_ON_OFF_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(6);
}
