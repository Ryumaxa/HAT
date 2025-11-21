package org.example.manual_udp.ballu_oneair_asp_100;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.log_utils.LogChecker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

public class Asp100SettingTest {
    // TODO: предусмотреть переключение с UDP на MQTT в рамках одного теста
    // TODO: предусмотреть все доступные языки (RU-EN)
    // TODO: вынести в отдельные методы проверку полей ввода и чекбоксов
    static CloseableAndroidDriver driver;

    @BeforeAll
    static void setup() {
        try {
            driver = DriverBuilder.getAndroidDriver();
            // Проверка, что карточка открылась
            boolean isCardOpened = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.DEVICE_CARD).click(), 15000, "DeviceConnectionViewModel", "deviceType=" + BalluAsp100MainScreen.DEVICE_TYPE
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
        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_PARAMETERS).click();
        Thread.sleep(100);
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Модель\")").isDisplayed());
        Assertions.assertTrue(driver.select("new UiSelector().className(\"android.view.View\").instance(4)").isDisplayed());
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Продолжить\")").isDisplayed());
        driver.navigate().back();
    }

    @Test
    void whenSoundCheckboxClicked_shouldPrintLogsAboutEnabledOrDisabledSound() {
        boolean isTurnOff = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.SETTING_SOUND).click(), "DeviceUtils", "CmdVolume", "enabled=0"
        );
        if (isTurnOff) {
            boolean isTurnOn = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.SETTING_SOUND).click(), "DeviceUtils", "CmdVolume", "enabled=1"
            );
            Assertions.assertTrue(isTurnOn);
        } else {
            isTurnOff = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.SETTING_SOUND).click(), "DeviceUtils", "CmdVolume", "enabled=0"
            );
            Assertions.assertTrue(isTurnOff);
        }
    }

    @Test
    void filterResourceCancelButtonTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_FILTER_RESOURCE).click();
        Thread.sleep(100);
        Assertions.assertEquals("Сбросить счетчик расходных материалов?", driver.select("new UiSelector().resourceId(\"com.hommyn.app:id/alertTitle\")").getText());
        Assertions.assertEquals("ОТМЕНА", driver.select(BalluAsp100MainScreen.FILTER_RESOURCE_CANCEL_BUTTON).getText());
        driver.select(BalluAsp100MainScreen.FILTER_RESOURCE_CANCEL_BUTTON).click();
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.select("new UiSelector().resourceId(\"com.hommyn.app:id/alertTitle\")"));
    }

    @Test
    void filterResourceResetButtonTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_FILTER_RESOURCE).click();
        Thread.sleep(100);
        Assertions.assertEquals("СБРОСИТЬ", driver.select(BalluAsp100MainScreen.FILTER_RESOURCE_RESET_BUTTON).getText());
        boolean isReset = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.FILTER_RESOURCE_RESET_BUTTON).click(), "UdpConnection", "CmdExpendables", "value=[0]"
        );
        Assertions.assertTrue(isReset);
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.select("new UiSelector().resourceId(\"com.hommyn.app:id/alertTitle\")"));
    }

    @Test
    // TODO: вынести проверку чекбоксов в отдельный метод
    void whenLedCheckboxClicked_shouldPrintLogsAboutEnabledOrDisabledLed() {
        boolean isTurnOff = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.SETTING_LED_OFF).click(), "DeviceUtils", "CmdBacklight", "enabled=0"
        );
        if (isTurnOff) {
            boolean isTurnOn = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.SETTING_LED_OFF).click(), "DeviceUtils", "CmdBacklight", "enabled=1"
            );
            Assertions.assertTrue(isTurnOn);
        } else {
            isTurnOff = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.SETTING_LED_OFF).click(), "DeviceUtils", "CmdBacklight", "enabled=0"
            );
            Assertions.assertTrue(isTurnOff);
        }
    }

    @Test
    void accessControlScreenTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_ACCESS_CONTROL).click();
        Thread.sleep(100);
        boolean isTurnOff = LogChecker.checkLogsInBackground(
                () -> driver.select(BalluAsp100MainScreen.ACCESS_CONTROL_SWITCH).click(), "DeviceUtils", "CmdAccessControl", "enabled=0"
        );
        if (isTurnOff) {
            boolean isTurnOn = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.ACCESS_CONTROL_SWITCH).click(), "DeviceUtils", "CmdAccessControl", "enabled=1"
            );
            Assertions.assertTrue(isTurnOn);
        } else {
            isTurnOff = LogChecker.checkLogsInBackground(
                    () -> driver.select(BalluAsp100MainScreen.ACCESS_CONTROL_SWITCH).click(), "DeviceUtils", "CmdAccessControl", "enabled=0"
            );
            Assertions.assertTrue(isTurnOff);
        }

        driver.select(BalluAsp100MainScreen.ACCESS_SHARE_BUTTON).click();
        Thread.sleep(100);
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.ACCESS_SHARE_QR_CODE).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.ACCESS_SHARE_BUTTON).isEnabled());
        driver.navigate().back();
        driver.navigate().back();
    }

    @Test
    void firmwareUpdateScreenTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_FIRMWARE_VERSION).click();
        Assertions.assertTrue(driver.select("new UiSelector().text(\"Обновлений не найдено\")").isDisplayed());
        driver.select(BalluAsp100MainScreen.FIRMWARE_UPDATE_OK_BUTTON).click();
        Thread.sleep(100);
        driver.select(BalluAsp100MainScreen.SETTING_BUTTON).click();
    }

    @Test
    void networkStateScreenTest() throws InterruptedException {
        driver.select(BalluAsp100MainScreen.SETTING_NETWORK_STATE).click();
        Thread.sleep(100);
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.CLOUD_IMAGE).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.ROUTER_IMAGE).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.PHONE_IMAGE).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.DEVICE_IMAGE).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.LINE_1).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.LINE_2).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.LINE_3).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.LINE_4).isDisplayed());

        Assertions.assertEquals("Облако", driver.select(BalluAsp100MainScreen.CLOUD_TEXT).getText());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.ROUTER_TEXT).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.PHONE_TEXT).isDisplayed());
        Assertions.assertTrue(driver.select(BalluAsp100MainScreen.DEVICE_TEXT).getText().contains(BalluAsp100MainScreen.DEVICE_NAME));

        driver.navigate().back();
    }

    @Test
    void forgetDeviceTest() {
        driver.select(BalluAsp100MainScreen.SETTING_FORGET_DEVICE).click();
        Assertions.assertTrue(driver.select("new UiSelector().resourceId(\"android:id/message\")").getText().contains(BalluAsp100MainScreen.DEVICE_NAME));
        driver.select(BalluAsp100MainScreen.SETTING_INPUT_CANCEL).click();
    }
}
