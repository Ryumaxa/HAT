package org.example.ui_utils.service;

import org.example.ui_utils.elements_classes.for_parsing.ui_json.Root;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс для соотнесения устройства с его JSON-файлом
 */
public class FileService {
	private final JsonParser jsonParser;
	
	public FileService(JsonParser jsonParser) {
		this.jsonParser = jsonParser;
	}
	
	public HashMap<Integer, File> getTypePathMap(String directoryPath) throws IOException {
		HashMap<Integer, File> typePathMap = new HashMap<>();
		File directory = new File(directoryPath);
		File[] files = directory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					Root root = jsonParser.parseRootElement(file);
					typePathMap.put(root.getType(), file);
				}
			}
		}
		return typePathMap;
	}
	
}
