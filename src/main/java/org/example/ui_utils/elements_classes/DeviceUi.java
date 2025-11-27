package org.example.ui_utils.elements_classes;

import lombok.Getter;
import lombok.Setter;
import org.example.ui_utils.elements_classes.layout_elements.*;

import java.util.ArrayList;

/**
 * Класс для описания UI устройства
 * Общие для всех устройств элементы заданы по умолчанию
 */
@Getter @Setter
public class DeviceUi {
	private int type;
	private String deviceName;
	
	private RootElement rootElement;
	private ArrayList<TopElement> topElements;
	private ArrayList<MiddleElement> middleElements;
	private ArrayList<BottomElement> bottomElements;
	private ArrayList<MusicElement> musicElements;
	
	public DeviceUi(RootElement rootElement,
	                ArrayList<TopElement> topElements,
	                ArrayList<MiddleElement> middleElements,
	                ArrayList<BottomElement> bottomElements,
	                ArrayList<MusicElement> musicElements) {
		this.rootElement = rootElement;
		this.topElements = topElements;
		this.middleElements = middleElements;
		this.bottomElements = bottomElements;
		this.musicElements = musicElements;
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
				       "\n\t}";
	}
}
