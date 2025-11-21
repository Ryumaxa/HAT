package org.example;

import org.example.ui_utils.UiParser;
import org.example.ui_utils.elements_classes.layout.*;

import java.util.ArrayList;

public class Sandbox {
    public static void main(String[] args) throws Exception {
//        CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();

        UiParser parser = new UiParser("src/main/resources/jsons/ASP 100.json");
        RootElement rootElement = parser.parseRootElement();
        ArrayList<BottomElement> bottomElements = parser.parseBottomButtons();
        ArrayList<TopElement> topElements = parser.parseTopElements();
        ArrayList<MiddleElement> middleElements = parser.parseMiddleElements();
        ArrayList<MusicElement> musicElements = parser.parseMusicElements();

        System.out.println("----------ROOT ELEMENT--------");
        System.out.println(rootElement);

        System.out.println("----------TOP ELEMENTS--------");
        for (TopElement element : topElements) {
            System.out.println(element);
        }

        System.out.println("----------MIDDLE ELEMENTS--------");
        for (MiddleElement element : middleElements) {
            System.out.println(element);
        }

        System.out.println("----------BOTTOM ELEMENTS--------");
        for (BottomElement button : bottomElements) {
            System.out.println(button);
        }

        System.out.println("----------MUSIC ELEMENTS--------");
        for (MusicElement element : musicElements) {
            System.out.println(element);
        }
    }
}
