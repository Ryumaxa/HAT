package org.example.devices_control_via_udp_tests;

import io.appium.java_client.AppiumBy;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO: запускать драйвер единожды в самом начале, потом использовать готовые методы для вызова каждой кнопки (передавая драйвер в метод)
public class Asp100Tests {

    final String DEVICE_NAME = "ASP100 TEST";
    final String CARD_XPATH = String.format("//*[@text='%s']", DEVICE_NAME);

    @BeforeEach
    void setup() {
        // TODO: открытие нужной активити перед тестом
    }

    // TODO: вынести этот тест в стартовые условия (карточка должна бытьоткрыта перед каждым тестом элементов управления)
    @Test
    void whenAsp100CardClicked_shouldOpenAsp100Card() {
        try (CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver()) {
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(AppiumBy.xpath(CARD_XPATH)).click(), "DeviceConnectionViewModel", "deviceType=69"
            );
            Assertions.assertTrue(logsFound);
        }
    }

    @Test
    void whenPowerButtonClicked_shouldOpenAsp100Card() {
        try (CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver()) {
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.hommyn.app:id/ltFeature\").instance(6)")).click(), "DeviceUtils", "mode="
            );
            Assertions.assertTrue(logsFound);
        }
    }
}
