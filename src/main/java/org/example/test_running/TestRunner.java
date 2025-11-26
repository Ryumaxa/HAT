package org.example.test_running;

import org.example.ui_utils.UiParser;
import org.example.ui_utils.elements_classes.DeviceUi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Основной класс для запуска тестов
 */
public class TestRunner {
	
	public static void main(String[] args) throws IOException {
		UiParser parser = new UiParser();
		HashMap<Integer, String> deviceList = parser.getDeviceList("src/main/resources/devices.json");
		HashMap<Integer, File> typeFileMap = parser.getTypePathMap("src/main/resources/jsons");
		List<DeviceUi> deviceUis = new ArrayList<>();
		
		// Итерация по мапе
		Set<Integer> keys = deviceList.keySet();
		for (int k : keys) {
			// Берем тип
			int type = k;
			
			// Берем имя устройства (как в хомине будет записано)
			String name = deviceList.get(k);
			
			// Получаем файл с макетом UI этого конкретного устройства
			File file = typeFileMap.get(type);
			
			// Создаем отдельный парсер для JSON с макетом экрана
			if (file != null) {
				UiParser unitParser = new UiParser(file.getPath());
				
				// Создаем объект с моделью экрана управления устройством
				DeviceUi deviceUi = unitParser.getDeviceUi();
				
				deviceUi.setType(type);
				deviceUi.setDeviceName(name);
				
				// Добавления модели экрана устройства в лист, для которого будут запускаться тесты
				deviceUis.add(deviceUi);
			}
		}
		
		for (DeviceUi deviceUi : deviceUis) {
			System.out.println(deviceUi);
		}
		
	}
}
