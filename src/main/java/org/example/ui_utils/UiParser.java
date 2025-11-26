package org.example.ui_utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.for_parsing.*;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceData;
import org.example.ui_utils.elements_classes.for_parsing.device_list.DeviceListRoot;
import org.example.ui_utils.elements_classes.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO: ------------------------------------------------
// TODO: класс - костыльная помойка, надо навести порядок
// TODO: починить индексацию при использовании stream() СРОЧНО (а мб и не надо наоборот)
// TODO: ------------------------------------------------
@NoArgsConstructor
public class UiParser {
	private RootElement rootElement;
    private ArrayList<BottomElement> bottomElements;
    private ArrayList<TopElement> topElements;
    private ArrayList<MiddleElement> middleElements;
    private ArrayList<MusicElement> musicElements;
    private Root root;

    public UiParser(String path) throws IOException {
        bottomElements = new ArrayList<>();
        topElements = new ArrayList<>();
        middleElements = new ArrayList<>();
        musicElements = new ArrayList<>();
		rootElement = new RootElement();

        init(path);
    }

    private void init(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        root = objectMapper.readValue(file, Root.class);
    }

    public DeviceUi getDeviceUi() throws IOException {
		parseRootElement();
		parseTopElements();
		parseMiddleElements();
		parseBottomElements();
		parseMusicElements();
	    return new DeviceUi(rootElement, topElements, middleElements, bottomElements, musicElements);
    }

    public RootElement parseRootElement() throws IOException {
	    rootElement = new RootElement(root.getName(), root.getType(), root.getClazz());
        return rootElement;
    }

    public ArrayList<TopElement> parseTopElements() {
        for (int i = 0; i < root.getLayout().getTop().length; i++) {
            String type = findType(root.getLayout().getTop(), i);
            String name = findName(root.getLayout().getTop(), i).replace("- ", "").replace(" ", "_");
            if (topElements.stream().noneMatch(x -> x.getName().equals(name))) {
                topElements.add(new TopElement(i, type, name));
            }
        }
        return topElements;
    }

    public ArrayList<MiddleElement> parseMiddleElements() {
        for (int i = 0; i < root.getLayout().getMiddle().length; i++) {
            String type = findType(root.getLayout().getMiddle(), i);
            String name = findName(root.getLayout().getMiddle(), i).replace(" ", "_");
            Limit limit = findLimit(root.getLayout().getMiddle(), i);

            middleElements.add(new MiddleElement(i, type, name, limit));
        }
        return middleElements;
    }

    public ArrayList<BottomElement> parseBottomElements() {
        for (int i = 0; i < root.getLayout().getBottom().length; i++) {
            String type = findType(root.getLayout().getBottom(), i);
            String name = findName(root.getLayout().getBottom(), i).replace(" ", "_");
            int mode = findMode(root.getLayout().getBottom(), i);
            bottomElements.add(new BottomElement(i, type, name, mode));
        }
        return bottomElements;
    }

    public ArrayList<MusicElement> parseMusicElements() {
        for (int i = 0; i < root.getLayout().getMusic().length; i++) {
            String type = findType(root.getLayout().getMusic(), i);
            String name = findName(root.getLayout().getMusic(), i).replace(" ", "_");
            int value = findValue(root.getLayout().getMusic(), i);

            if (musicElements.stream().noneMatch(x -> x.getName().equals(name))) {
                musicElements.add(new MusicElement(i, type, name, value));
            }
        }
        return musicElements;
    }

    private String findType(Bottom[] bottom, int i) {
        return bottom[i].getType().toUpperCase();
    }

    private String findType(Top[] top, int i) {
        return top[i].getType().toUpperCase();
    }

    private String findType(Middle[] top, int i) {
        return top[i].getType().toUpperCase();
    }

    private String findType(Music[] music, int i) {
        return music[i].getType().toUpperCase();
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
        if (bottom[i].getAction() != null &&
                bottom[i].getAction().getProgram() != null) {
            return bottom[i].getAction().getProgram()[0];
        } else if (bottom[i].getAction() != null &&
                bottom[i].getAction().getProgramData() != null) {
            return bottom[i].getAction().getProgramData().getProgram()[0];
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

    public List<BottomElement> getModeButtons() throws IOException {
        if (bottomElements.isEmpty()) {
            bottomElements = this.parseBottomElements();
        }
        return bottomElements.stream().filter(a -> a.getMode() != -1).toList();
    }

    public List<MusicElement> getMusicButtons() throws IOException {
        if (musicElements.isEmpty()) {
            musicElements = this.parseMusicElements();
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
					root = objectMapper.readValue(file, Root.class);
					typePathMap.put(root.getType(), file);
				}
			}
		}
		return typePathMap;
	}

}
