package org.example.ui_utils.elements_classes;

import lombok.Getter;
import lombok.Setter;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.layout_elements.*;

import java.util.ArrayList;

/**
 * Класс для описания UI устройства
 * Общие для всех устройств элементы заданы по умолчанию
 */
@Getter @Setter
public class DeviceUi {
	private static final ElementsCreator ELEMENTS_CREATOR = new ElementsCreator();
	
	// Общие элементы экрана управления устройством
	public static final String BACK_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnLeading\")";
	public static final String SETTING_BUTTON = "new UiSelector().resourceId(\"com.hommyn.app:id/btnSettings\")";
	// Общие пункты в меню настроек
	public static final String SETTING_DEVICE_NAME = ELEMENTS_CREATOR.createSettingElementWithIndex(0);
	public static final String SETTING_DEVICE_PLACE = ELEMENTS_CREATOR.createSettingElementWithIndex(1);
	public static final String SETTING_DEVICE_ROOM = ELEMENTS_CREATOR.createSettingElementWithIndex(2);
	public static final String SETTING_DEVICE_GROUP = ELEMENTS_CREATOR.createSettingElementWithIndex(3);
	public static final String SETTING_DEVICE_PARAMETERS = ELEMENTS_CREATOR.createSettingElementWithIndex(4);
	public static final String SETTING_ACCESS_CONTROL = ELEMENTS_CREATOR.createButtonWithText("Контроль прав");
	public static final String SETTING_FIRMWARE_VERSION = "new UiSelector().resourceId(\"com.hommyn.app:id/btnFirmwareUpdate\")";
	public static final String SETTING_NETWORK_STATE = ELEMENTS_CREATOR.createButtonWithText("Состояние сети");
	public static final String SETTING_FORGET_DEVICE = ELEMENTS_CREATOR.createButtonWithText("Забыть устройство");
	// Поля ввода в настройках
	public static final String SETTING_INPUT_FIELD = "new UiSelector().resourceId(\"com.hommyn.app:id/etInput\")";
	public static final String  SETTING_INPUT_SAVE = "new UiSelector().resourceId(\"android:id/button1\")";
	public static final String  SETTING_INPUT_CANCEL = "new UiSelector().resourceId(\"android:id/button2\")";
	public static final String  SETTING_INPUT_DELETE = "new UiSelector().resourceId(\"android:id/button3\")";
	// Доп элементы в настройках
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
	
	private int type;
	private String deviceName;
	
	private RootElement rootElement;
	private ArrayList<TopElement> topElements;
	private ArrayList<MiddleElement> middleElements;
	private ArrayList<BottomElement> bottomElements;
	private ArrayList<MusicElement> musicElements;
	private ArrayList<SettingElement> settingElements;
	
	public DeviceUi(RootElement rootElement,
	                ArrayList<TopElement> topElements,
	                ArrayList<MiddleElement> middleElements,
	                ArrayList<BottomElement> bottomElements,
	                ArrayList<MusicElement> musicElements,
	                ArrayList<SettingElement> settingElements) {
		this.rootElement = rootElement;
		this.topElements = topElements;
		this.middleElements = middleElements;
		this.bottomElements = bottomElements;
		this.musicElements = musicElements;
		this.settingElements = settingElements;
	}
	
	@Override
	public String toString() {
		return "DeviceUi{" +
				       "\n\ttype=" + type +
				       "\n\tdeviceName='" + deviceName + '\'' +
				       "\n\trootElement=" + rootElement +
				       "\n\ttopElements=" + topElements +
				       "\n\tmiddleElements=" + middleElements +
				       "\n\tbottomElements=" + bottomElements +
				       "\n\tmusicElements=" + musicElements +
				       "\n\tsettingElements=" + settingElements +
				       "\n\t}";
	}
}
