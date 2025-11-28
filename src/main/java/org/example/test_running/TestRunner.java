package org.example.test_running;

import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.service.UiService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Основной класс для запуска тестов
 */
public class TestRunner {

	public static void main(String[] args) throws IOException {
		UiService uiService = new UiService();
		ArrayList<DeviceUi> deviceUis = uiService.getDevicesUi("src/main/resources/devices.json", "src/main/resources/ui_jsons");
		for (DeviceUi deviceUi : deviceUis) {
			System.out.println(deviceUi);
		}
		
		// TODO: придумать, куда и в каком виде прокидывать отфильтрованные коллекции элементов (кнопки режимов, кнопки музыки и т.д.)
		// TODO: сделать базовые тесты по типу элемента на основе входных параметров (для кнопок режимов, для ползунков, для чекбоксов и т.д.)

	}
}
