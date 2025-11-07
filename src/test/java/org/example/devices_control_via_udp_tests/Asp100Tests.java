package org.example.devices_control_via_udp_tests;

import io.appium.java_client.AppiumBy;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.*;

public class Asp100Tests {

    static CloseableAndroidDriver driver;

    @BeforeAll
    static void setup() {
        try {
            driver = DriverBuilder.getAndroidDriver();
            // Проверка, что карточка открылась
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), "DeviceConnectionViewModel", "deviceType=69"
            );
            Assertions.assertTrue(isCardOpened);
            // Проверка, что устройство отключено (в противном случае отключение с повторной проверкой)
            boolean isDeviceOff = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.POWER_ON_OFF_BUTTON).click(), "DeviceUtils", "mode=00"
            );
            if (!isDeviceOff) {
                isDeviceOff = LogChecker.checkLogsInBackground(
                        () -> driver.select(BalluAsp100MainScreen.POWER_ON_OFF_BUTTON).click(), "DeviceUtils", "mode=00"
                );
            }
            Assertions.assertTrue(isDeviceOff);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @AfterAll
    static void close() {
        driver.quit();
    }

    @Test
    void whenFanModeButtonClicked_shouldSwitchToMode5() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FAN_MODE_BUTTON).click(), "DeviceUtils", "mode=05"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenNightModeButtonClicked_shouldSwitchToMode3() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.NIGHT_MODE_BUTTON).click(), "DeviceUtils", "mode=03"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenTurboModeButtonClicked_shouldSwitchToMode4() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.TURBO_MODE_BUTTON).click(), "DeviceUtils", "mode=04"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenMelodiesButtonClicked_shouldPrintLogsAboutMelodies() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click(), "DeviceControlsUtils", "Melodies"
        );
        Assertions.assertTrue(logsFound);
        driver.navigate().back();
    }

    @Test
    void whenRainSoundButtonClicked_shouldPrintLogsAboutAmountValueIs1() {
        driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.MELODIES_BUTTON)).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.RAIN_SOUND_BUTTON).click(), "DeviceUtils", "value=1"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenSeaSoundButtonClicked_shouldPrintLogsAboutAmountValueIs2() {
        driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.MELODIES_BUTTON)).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.SEA_SOUND_BUTTON).click(), "DeviceUtils", "value=2"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenForestSoundButtonClicked_shouldPrintLogsAboutAmountValueIs3() {
        driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.MELODIES_BUTTON)).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FOREST_SOUND_BUTTON).click(), "DeviceUtils", "value=3"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenBirdsSoundButtonClicked_shouldPrintLogsAboutAmountValueIs4() {
        driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.MELODIES_BUTTON)).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.BIRDS_SOUND_BUTTON).click(), "DeviceUtils", "value=4"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenFireSoundButtonClicked_shouldPrintLogsAboutAmountValueIs5() {
        driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.MELODIES_BUTTON)).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FIRE_SOUND_BUTTON).click(), "DeviceUtils", "value=5"
        );
        Assertions.assertTrue(logsFound);
    }
}
