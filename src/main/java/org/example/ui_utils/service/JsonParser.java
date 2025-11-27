package org.example.ui_utils.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui_utils.elements_classes.for_parsing.ui_json.Root;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceData;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceListRoot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс для парсинга JSON-фалов
 */
public class JsonParser {
	private final ObjectMapper objectMapper;
	
	public JsonParser() {
		this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public Root parseRootElement(File file) throws IOException {
		return objectMapper.readValue(file, Root.class);
	}
	
	public HashMap<Integer, String> parseDeviceList(String path) throws IOException {
		HashMap<Integer, String> deviceMap = new HashMap<>();
		File file = new File(path);
		DeviceListRoot deviceListRoot = objectMapper.readValue(file, DeviceListRoot.class);
		DeviceData[] devices = deviceListRoot.getDevicesData();
		for (DeviceData device : devices) {
			deviceMap.put(device.getType(), device.getName());
		}
		return deviceMap;
	}
}
