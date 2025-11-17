package org.example.manual_udp.ballu_oneair_asp_100;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

public class Asp100SettingTest {
    // TODO: предусмотреть переключение с UDP на MQTT в рамках одного теста
    // TODO: предусмотреть все доступные языки (RU-EN)
    // TODO: вынести все элементы в класс константами
    // TODO: в списке настроек проверить порядок расположения настроект (getLocation и bound не работаеют кореектно из-за скроллинга)
    static CloseableAndroidDriver driver;

    @BeforeAll
    static void setup() {
        try {
            driver = DriverBuilder.getAndroidDriver();
            // Проверка, что карточка открылась
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), "DeviceConnectionViewModel", "deviceType=" + BalluAsp100MainScreen.DEVICE_TYPE
            );
            Assertions.assertTrue(isCardOpened);
            driver.select(BalluAsp100MainScreen.SETTING_BUTTON).click();
            Assertions.assertTrue(driver.select("new UiSelector().text(\"Настройки устройства\")").isDisplayed());
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
    void deviceNameTextFieldComplexTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_NAME).click();
        Thread.sleep(100);
        String prevName = driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).getText();
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("test_device");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_device\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_NAME).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("not_test_device");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_CANCEL).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_device\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_NAME).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys(prevName);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"" + prevName + "\")").isDisplayed());
    }

    @Test
    void devicePlaceTextFieldComplexTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_PLACE).click();
        Thread.sleep(100);
        String prevPlace = driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).getText();
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("test_place");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_place\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_PLACE).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("not_test_place");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_CANCEL).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_place\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_PLACE).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys(prevPlace);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"" + prevPlace + "\")").isDisplayed());
    }

    @Test
    void deviceRoomTextFieldComplexTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
        Thread.sleep(100);
        String prevRoom = driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).getText();
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("test_room");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_room\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("not_test_room");
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_CANCEL).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"test_room\")").isDisplayed());

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_DELETE).click();
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.select("new UiSelector().text(\"test_room\")"));

        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys(prevRoom);
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"" + prevRoom + "\")").isDisplayed());
    }

    @Test
    void whenDeviceGroupButtonClicked_shouldOpenAddGroupScreen() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_GROUP).click();
        Thread.sleep(100);
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Выбранные устройства\")").isDisplayed());
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Продолжить\")").isDisplayed());
        driver.navigate().back();
    }

    @Test
    void whenDeviceParametersButtonClicked_shouldOpenDeviceParametersScreen() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_GROUP).click();
        Thread.sleep(100);
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Модель\")").isDisplayed());
        // TODO: сюда же добавить проверку наличия карточек
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Продолжить\")").isDisplayed());
        driver.navigate().back();
    }

    // TODO: дописать тесты под звук и все, что ниже него



}
