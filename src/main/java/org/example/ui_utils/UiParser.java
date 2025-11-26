package org.example.ui_utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.for_parsing.*;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceData;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceListRoot;
import org.example.ui_utils.elements_classes.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

// TODO: --------------------------------------------------------------------------------
// TODO: класс - костыльная помойка, надо навести порядок
// TODO: починить индексацию при использовании stream() СРОЧНО (а мб и не надо наоборот)
// TODO:убрать дублирование методов только когда будут готовы все
// TODO: --------------------------------------------------------------------------------
public class UiParser {
	private final ObjectMapper objectMapper;
	
	public UiParser() {
		this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public DeviceUi parseFromFile(String path) throws IOException {
		Root root = objectMapper.readValue(new File(path), Root.class);
		return getDeviceUi(root);
	}
	
	public DeviceUi getDeviceUi(Root root) {
		return new DeviceUi(parseRootElement(root),
				parseTopElements(root),
				parseMiddleElements(root),
				parseBottomElements(root),
				parseMusicElements(root));
	}
	
	public RootElement parseRootElement(Root root) {
		return new RootElement(root.getName(), root.getType(), root.getClazz());
	}
	
	public ArrayList<TopElement> parseTopElements(Root root) {
		Top[] top = root.getLayout().getTop();
		ArrayList<TopElement> topElements = new ArrayList<>();
		for (int i = 0; i < top.length; i++) {
			String type = findType(top, i, Top::getType);
			String name = findName(top, i).replace("- ", "").replace(" ", "_");
			if (topElements.stream().noneMatch(x -> x.getName().equals(name))) {
				topElements.add(new TopElement(i, type, name));
			}
		}
		return topElements;
	}
	
	public ArrayList<MiddleElement> parseMiddleElements(Root root) {
		Middle[] middle = root.getLayout().getMiddle();
		ArrayList<MiddleElement> middleElements = new ArrayList<>();
		for (int i = 0; i < middle.length; i++) {
			String type = findType(middle, i, Middle::getType);
			String name = findName(middle, i).replace(" ", "_");
			Limit limit = findLimit(middle, i);
			middleElements.add(new MiddleElement(i, type, name, limit));
		}
		return middleElements;
	}
	
	public ArrayList<BottomElement> parseBottomElements(Root root) {
		Bottom[] bottom = root.getLayout().getBottom();
		ArrayList<BottomElement> bottomElements = new ArrayList<>();
		for (int i = 0; i < bottom.length; i++) {
			String type = findType(bottom, i, Bottom::getType);
			String name = findName(bottom, i).replace(" ", "_");
			int mode = findMode(bottom, i);
			bottomElements.add(new BottomElement(i, type, name, mode));
		}
		return bottomElements;
	}
	
	public ArrayList<MusicElement> parseMusicElements(Root root) {
		Music[] music = root.getLayout().getMusic();
		ArrayList<MusicElement> musicElements = new ArrayList<>();
		if (root.getLayout().getMusic() != null) {
			for (int i = 0; i < music.length; i++) {
				String type = findType(music, i, Music::getType);
				String name = findName(music, i).replace(" ", "_");
				int value = findValue(music, i);
				
				if (musicElements.stream().noneMatch(x -> x.getName().equals(name))) {
					musicElements.add(new MusicElement(i, type, name, value));
				}
			}
		}
		return musicElements;
	}
	
	private <T> String findType(T[] elements, int i, Function<T, String> typeExtractor) {
		return typeExtractor.apply(elements[i]).toUpperCase();
	}
	
	private String findName(Bottom[] bottom, int i) {
		if (bottom[i].getTitle() != null) {
			return bottom[i].getTitle().get("en-US").toUpperCase();
		} else if (bottom[i].getFeature() != null) {
			return bottom[i].getFeature().toUpperCase();
		} else if (bottom[i].getAction().getProgramData() != null) {
			return bottom[i].getAction().getProgramData().getField().toUpperCase();
		} else {
			return "AUTO";
		}
	}
	
	private String findName(Top[] top, int i) {
		return top[i].getTitle().get("en-US").toUpperCase();
	}
	
	private String findName(Music[] music, int i) {
		if (music[i].getTitle() != null) {
			return music[i].getTitle().get("en-US").toUpperCase();
		} else {
			return "noname";
		}
	}
	
	private String findName(Middle[] middle, int i) {
		if (middle[i].getFeature() != null) {
			return middle[i].getFeature().toUpperCase();
		} else {
			return "noname";
		}
	}
	
	private int findMode(Bottom[] bottom, int i) {
		Action action = bottom[i].getAction();
		if (action != null &&
				    action.getProgram() != null) {
			return action.getProgram()[0];
		} else if (action != null &&
				           action.getProgramData() != null) {
			return action.getProgramData().getProgram()[0];
		} else {
			return -1;
		}
	}
	
	private int findValue(Music[] music, int i) {
		if (music[i].getAction() != null) {
			return music[i].getAction().getAmount();
		} else {
			return -1;
		}
	}
	
	private Limit findLimit(Middle[] middle, int i) {
		return middle[i].getLimit();
	}
	
	public List<BottomElement> getModeButtons(List<BottomElement> bottomElements, Root root) {
		if (bottomElements.isEmpty()) {
			bottomElements = this.parseBottomElements(root);
		}
		return bottomElements.stream().filter(a -> a.getMode() != -1).toList();
	}
	
	public List<MusicElement> getMusicButtons(List<MusicElement> musicElements, Root root) {
		if (musicElements.isEmpty()) {
			musicElements = this.parseMusicElements(root);
		}
		return musicElements.stream().filter(a -> a.getType().equals("BUTTON")).toList();
	}
	
	public HashMap<Integer, String> getDeviceList(String path) throws IOException {
		HashMap<Integer, String> deviceMap = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(path);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		DeviceListRoot deviceListRoot = objectMapper.readValue(file, DeviceListRoot.class);
		DeviceData[] devices = deviceListRoot.getDevicesData();
		for (DeviceData device : devices) {
			deviceMap.put(device.getType(), device.getName());
		}
		return deviceMap;
	}
	
	public HashMap<Integer, File> getTypePathMap(String path) throws IOException {
		HashMap<Integer, File> typePathMap = new HashMap<>();
		File directory = new File(path);
		File[] files = directory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					Root root = objectMapper.readValue(file, Root.class);
					typePathMap.put(root.getType(), file);
				}
			}
		}
		return typePathMap;
	}
	
}
