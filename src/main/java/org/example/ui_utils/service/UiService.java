package org.example.ui_utils.service;

import org.example.ui_utils.elements_classes.DeviceUi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Класс-сервис для формирования всех моделей экранов устройств
 */
public class UiService {
	private final FileService fileService;
	private final JsonParser jsonParser;
	private final UiMapper uiMapper;
	
	public UiService() {
		this.jsonParser = new JsonParser();
		this.fileService = new FileService(jsonParser);
		this.uiMapper = new UiMapper();
	}
	
	public ArrayList<DeviceUi> getDevicesUi(String deviceListPath, String jsonsDirectoryPath) throws IOException {
		HashMap<Integer, String> deviceList = jsonParser.parseDeviceList(deviceListPath);
		HashMap<Integer, File> typeFileMap = fileService.getTypePathMap(jsonsDirectoryPath);
		ArrayList<DeviceUi> deviceUis = new ArrayList<>();
		Set<Integer> keys = deviceList.keySet();
		for (int type : keys) {
			String name = deviceList.get(type);
			File file = typeFileMap.get(type);
			if (file != null) {
				DeviceUi deviceUi = uiMapper.getDeviceUi(jsonParser.parseRootElement(file));
				deviceUi.setType(type);
				deviceUi.setDeviceName(name);
				deviceUis.add(deviceUi);
			}
		}
		return deviceUis;
	}
}
