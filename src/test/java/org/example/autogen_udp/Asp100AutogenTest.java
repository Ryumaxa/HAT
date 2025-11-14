package org.example.autogen_udp;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.test_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.UiParser;
import org.example.ui_utils.elements_classes.BottomButton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Asp100AutogenTest {
    static CloseableAndroidDriver driver;
    static UiParser parser;
    static ArrayList<BottomButton> bottomButtons;
    static List<BottomButton> modeButtons;
    static ElementsCreator elementsCreator;

    @BeforeAll
    static void setup() {
        parser = new UiParser("src/main/resources/jsons/ASP 100.json");
        try {
            elementsCreator = new ElementsCreator();
            bottomButtons = parser.parseBottomButtons();
            modeButtons = parser.getModeButtons();
            driver = DriverBuilder.getAndroidDriver();

            // Проверка, что карточка открылась
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), "DeviceConnectionViewModel", "deviceType=69"
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
        for (BottomButton modeButton : modeButtons) {
            logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.select(elementsCreator.createButtonWithIndex(modeButton.getIndex())).click(), "DeviceUtils", "mode=0" + modeButton.getMode()
            );
        }
        Assertions.assertTrue(logsFound);
        // TODO: не проходит потому что из JSON для кнопки Вкл/Откл берется только одно из значений
    }

}
