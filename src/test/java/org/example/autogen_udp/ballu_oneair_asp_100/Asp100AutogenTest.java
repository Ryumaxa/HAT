package org.example.autogen_udp.ballu_oneair_asp_100;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.log_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.UiParser;
import org.example.ui_utils.elements_classes.layout.BottomElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Asp100AutogenTest {
    static CloseableAndroidDriver driver;
    static UiParser parser;
    static ArrayList<BottomElement> bottomElements;
    static List<BottomElement> modeButtons;
    static ElementsCreator elementsCreator;

    @BeforeAll
    static void setup() throws IOException {
        parser = new UiParser("src/main/resources/jsons/ASP 100.json");
        try {
            elementsCreator = new ElementsCreator();
            bottomElements = parser.parseBottomButtons();
            modeButtons = parser.getModeButtons();
            driver = DriverBuilder.getAndroidDriver();

            // Проверка, что карточка открылась
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), "DeviceConnectionViewModel", "deviceType=" + BalluAsp100MainScreen.DEVICE_TYPE
            );
            Assertions.assertTrue(isCardOpened);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @AfterAll
    static void close() {
//        driver.backToDeviceList();
        driver.quit();
    }

    @Test
    void modeButtonsComplexTest() {
        boolean logsFound = false;
        for (BottomElement modeButton : modeButtons) {
            logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.select(elementsCreator.createButtonWithIndex(modeButton.getIndex())).click(), "DeviceUtils", "mode=0" + modeButton.getMode()
            );
        }
        Assertions.assertTrue(logsFound);
        // TODO: не проходит потому что из JSON для кнопки Вкл/Откл берется только одно из значений
    }
}
