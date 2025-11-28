package org.example.manual_udp.ballu_oneair_asp_100;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.log_utils.LogChecker;
import org.junit.jupiter.api.*;

public class Asp100Tests {

    // TODO: предусмотреть переключение с UDP на MQTT в рамках одного теста
    // TODO: предусмотреть все доступные языки (RU-EN)
    static CloseableAndroidDriver driver;

    @BeforeAll
    static void setup() {
        try {
            driver = DriverBuilder.getAndroidDriver();
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), "DeviceConnectionViewModel", "deviceType=" + BalluAsp100MainScreen.DEVICE_TYPE
            );
            Assertions.assertTrue(isCardOpened);
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
        driver.backToDeviceList();
        driver.quit();
    }

    @Test
    void deviceNameFieldShouldExistAndContainsCorrectName() {
        Assertions.assertEquals(BalluAsp100MainScreen.DEVICE_NAME, driver.selectById("com.hommyn.app:id/tvDeviceName").getText());
    }

    @Test
    void devicePlaceFieldShouldExist() {
        Assertions.assertTrue(driver.selectById("com.hommyn.app:id/tvDevicePlace").isDisplayed());
        // TODO: сравнить текст в поле со значением здания и комнаты в логах (д.б.)
    }

    @Test
    void co2FieldShouldExist() {
        Assertions.assertTrue(driver.select("new UiSelector().text(\"CO2 - ppm\")").isDisplayed());
    }

    @Test
    void inflowTemperatureFieldShouldExist() {
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Температура притока\")").isDisplayed());
    }

    @Test
    void outdoorAirValuesFieldShouldExist() {
        Assertions.assertTrue(driver.select("new UiSelector().textContains(\"Наружные показатели в городе\")").isDisplayed());
    }

    @Test
    void aqiFieldShouldExist() {
        Assertions.assertTrue(driver.selectById("com.hommyn.app:id/tvAQISuffix").isDisplayed());
    }

    @Test
    void pm25FieldShouldExist() {
        Assertions.assertTrue(driver.selectById("com.hommyn.app:id/tvPM2Suffix").isDisplayed());
    }

    @Test
    void pm10FieldShouldExist() {
        Assertions.assertTrue(driver.selectById("com.hommyn.app:id/tvPM10Suffix").isDisplayed());
    }

    @Test
    void outdoorTemperatureFieldShouldExist() {
        Assertions.assertTrue(driver.selectById("com.hommyn.app:id/tvTempSuffix").isDisplayed());
    }

    @Test
    void speedValueFieldShouldExist() {
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Cкорость\")").isDisplayed());
    }

    @Test
    void targetTemperatureValueFieldShouldExist() {
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Целевая температура\")").isDisplayed());
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
    void whenAutoModeButtonClicked_shouldSwitchToMode2() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.AUTO_MODE_BUTTON).click(), "DeviceUtils", "mode=02"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenTurboModeButtonClicked_shouldSwitchToMode4() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.TURBO_MODE_BUTTON).click(), "DeviceUtils", "mode=04"
        );
        Assertions.assertTrue(logsFound);
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.TURBO_TIMER).isDisplayed());
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
        driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.RAIN_SOUND_BUTTON).click(), "DeviceUtils", "value=1"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenSeaSoundButtonClicked_shouldPrintLogsAboutAmountValueIs2() {
        driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.SEA_SOUND_BUTTON).click(), "DeviceUtils", "value=2"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenForestSoundButtonClicked_shouldPrintLogsAboutAmountValueIs3() {
        driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FOREST_SOUND_BUTTON).click(), "DeviceUtils", "value=3"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenBirdsSoundButtonClicked_shouldPrintLogsAboutAmountValueIs4() {
        driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.BIRDS_SOUND_BUTTON).click(), "DeviceUtils", "value=4"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenFireSoundButtonClicked_shouldPrintLogsAboutAmountValueIs5() {
        driver.select(BalluAsp100MainScreen.MELODIES_BUTTON).click();
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FIRE_SOUND_BUTTON).click(), "DeviceUtils", "value=5"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenSpeedSliderSwitchedToMinValue_shouldPrintLogsAboutSpeed1() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.leftSliderMin(BalluAsp100MainScreen.SPEED_SLIDER), "DeviceUtils", "speed=1"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenSpeedSliderSwitchedToMidValue_shouldPrintLogsAboutSpeed4() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.leftSliderMid(BalluAsp100MainScreen.SPEED_SLIDER), "DeviceUtils", "speed=4"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenSpeedSliderSwitchedToMaxValue_shouldPrintLogsAboutSpeed7() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.leftSliderMax(BalluAsp100MainScreen.SPEED_SLIDER), "DeviceUtils", "speed=7"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenTemperatureSliderSwitchedToMinValue_shouldPrintLogsAbout5Degrees() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.rightSliderMin(BalluAsp100MainScreen.TEMPERATURE_SLIDER), "DeviceUtils", "temperature=5.0"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenTemperatureSliderSwitchedToMidValue_shouldPrintLogsAbout15Degrees() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.rightSliderMid(BalluAsp100MainScreen.TEMPERATURE_SLIDER), "DeviceUtils", "temperature=15.0"
        );
        Assertions.assertTrue(logsFound);
    }

    @Test
    void whenTemperatureSliderSwitchedToMaxValue_shouldPrintLogsAbout25Degrees() {
        boolean logsFound = LogChecker.checkLogsInBackground(
                () -> driver.rightSliderMax(BalluAsp100MainScreen.TEMPERATURE_SLIDER), "DeviceUtils", "temperature=25.0"
        );
        Assertions.assertTrue(logsFound);
    }

}
