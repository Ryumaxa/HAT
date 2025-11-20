package org.example.screen_elements.ballu_asp100;

import org.example.ui_utils.ElementsCreator;

/**
 * Экран Ballu ASP-100
 * <p>
 * Тип - 69
 */
// TODO: вынести общие элементы в другой класс и наследовать все устройства от него
public class BalluAsp100MainScreen {
    private static final ElementsCreator ELEMENTS_CREATOR = new ElementsCreator();

    // Основные
    public static final int DEVICE_TYPE = 69;
    public static final String DEVICE_NAME = "ASP-100 TEST";
    public static final String DEVICE_CARD = ELEMENTS_CREATOR.scrollToElementWithText(DEVICE_NAME);
    public static final String BACK_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnLeading\")";
    public static final String SETTING_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnSettings\")";

    // Кнопки
    public static final String FAN_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(0);
    public static final String NIGHT_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(1);
    public static final String AUTO_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(2);
    public static final String TURBO_MODE_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(3);
    public static final String SCHEDULES_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(4);
    public static final String MELODIES_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(5);
    public static final String POWER_ON_OFF_BUTTON = ELEMENTS_CREATOR.createButtonWithIndex(6);

    // В выпадающем списке с мелодиями
    public static final String RAIN_SOUND_BUTTON = ELEMENTS_CREATOR.createButtonWithText("Звук дождя");
    public static final String SEA_SOUND_BUTTON = ELEMENTS_CREATOR.createButtonWithText("Звук моря");
    public static final String FOREST_SOUND_BUTTON = ELEMENTS_CREATOR.createButtonWithText("Звук леса");
    public static final String BIRDS_SOUND_BUTTON = ELEMENTS_CREATOR.createButtonWithText("Пение птиц");
    public static final String FIRE_SOUND_BUTTON = ELEMENTS_CREATOR.createButtonWithText("Звук костра");

    // Слайдеры
    public static final String SPEED_SLIDER = ELEMENTS_CREATOR.createSliderWithIndex(0);
    public static final String TEMPERATURE_SLIDER = ELEMENTS_CREATOR.createSliderWithIndex(1);

    // Другое
    public static final String TURBO_TIMER = "new UiSelector().resourceId(\"com.hommyn.app:id/ltContent\").instance(2)";

    // Настройки
    public static final String SETTING_DEVICE_NAME = ELEMENTS_CREATOR.createSettingElementWithIndex(0);
    public static final String SETTING_DEVICE_PLACE = ELEMENTS_CREATOR.createSettingElementWithIndex(1);
    public static final String SETTING_DEVICE_ROOM = ELEMENTS_CREATOR.createSettingElementWithIndex(2);
    public static final String SETTING_DEVICE_GROUP = ELEMENTS_CREATOR.createSettingElementWithIndex(3);
    public static final String SETTING_DEVICE_PARAMETERS = ELEMENTS_CREATOR.createSettingElementWithIndex(4);
    public static final String SETTING_SOUND = ELEMENTS_CREATOR.createButtonWithText("Звук");
    public static final String SETTING_FILTER_RESOURCE = ELEMENTS_CREATOR.createSettingElementWithIndex(5);
    public static final String SETTING_LED_OFF = ELEMENTS_CREATOR.createButtonWithText("Автоотключение LED индикации");
    public static final String SETTING_ACCESS_CONTROL = ELEMENTS_CREATOR.createSettingElementWithIndex(6);
    public static final String SETTING_FIRMWARE_VERSION = "new UiSelector().resourceId(\"com.hommyn.app:id/btnFirmwareUpdate\")";
    public static final String SETTING_NETWORK_STATE = ELEMENTS_CREATOR.createSettingElementWithIndex(7);
    public static final String SETTING_FORGET_DEVICE = ELEMENTS_CREATOR.createSettingElementWithIndex(8);

    // Поля ввода в настройках
    public static final String SETTING_INPUT_FIELD = "new UiSelector().resourceId(\"com.hommyn.app:id/etInput\")";
    public static final String  SETTING_INPUT_SAVE = "new UiSelector().resourceId(\"android:id/button1\")";
    public static final String  SETTING_INPUT_CANCEL = "new UiSelector().resourceId(\"android:id/button2\")";
    public static final String  SETTING_INPUT_DELETE = "new UiSelector().resourceId(\"android:id/button3\")";

    // Доп элементы в настройках
    public static final String FILTER_RESOURCE_CANCEL_BUTTON = "new UiSelector().resourceId(\"android:id/button2\")";
    public static final String FILTER_RESOURCE_RESET_BUTTON = "new UiSelector().resourceId(\"android:id/button1\")";
    public static final String ACCESS_CONTROL_SWITCH = "new UiSelector().resourceId(\"com.hommyn.app:id/swEnableAccessControl\")";
    public static final String ACCESS_SHARE_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnShare\")";
    public static final String ACCESS_SHARE_QR_CODE = "new UiSelector().resourceId(\"com.hommyn.app:id/qrCodeImage\")";
    public static final String FIRMWARE_UPDATE_OK_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnNoUpdatesOk\")";

    // Для экрана "Состояние сети"
    public static final String CLOUD_IMAGE = "new UiSelector().resourceId(\"com.hommyn.app:id/ivCloud\")";
    public static final String ROUTER_IMAGE = "new UiSelector().resourceId(\"com.hommyn.app:id/ivSingleRouter\")";
    public static final String PHONE_IMAGE = "new UiSelector().resourceId(\"com.hommyn.app:id/ivPhone\")";
    public static final String DEVICE_IMAGE = "new UiSelector().resourceId(\"com.hommyn.app:id/ivDevice\")";

    public static final String CLOUD_TEXT = "new UiSelector().resourceId(\"com.hommyn.app:id/tvCloud\")";
    public static final String ROUTER_TEXT = "new UiSelector().resourceId(\"com.hommyn.app:id/tvSingleRouter\")";
    public static final String PHONE_TEXT = "new UiSelector().resourceId(\"com.hommyn.app:id/tvPhone\")";
    public static final String DEVICE_TEXT = "new UiSelector().resourceId(\"com.hommyn.app:id/tvDevice\")";

    public static final String LINE_1 = "new UiSelector().resourceId(\"com.hommyn.app:id/linePhoneSingleRouterCloud\")";
    public static final String LINE_2 = "new UiSelector().resourceId(\"com.hommyn.app:id/lineDeviceSingleRouterCloud\")";
    public static final String LINE_3 = "new UiSelector().resourceId(\"com.hommyn.app:id/linePhoneSingleRouter\")";
    public static final String LINE_4 = "new UiSelector().resourceId(\"com.hommyn.app:id/lineDeviceSingleRouter\")";
}
