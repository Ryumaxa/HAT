package org.example.ui_utils.service;

import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.for_parsing.ui_json.*;
import org.example.ui_utils.elements_classes.layout_elements.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Класс для маппинга всех элементов UI
 */
public class UiMapper {
	
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
		Set<String> seenNames = new HashSet<>();
		for (int i = 0; i < top.length; i++) {
			String type = findType(top, i, Top::getType);
			String name = findName(top, i).replace("- ", "").replace(" ", "_");
			if (seenNames.add(name)) {
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
		if (music != null) {
			Set<String> seenNames = new HashSet<>();
			for (int i = 0; i < music.length; i++) {
				String type = findType(music, i, Music::getType);
				String name = findName(music, i).replace(" ", "_");
				int value = findValue(music, i);
				if (seenNames.add(name)) {
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

}
