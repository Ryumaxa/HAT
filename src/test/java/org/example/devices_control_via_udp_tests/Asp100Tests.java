package org.example.devices_control_via_udp_tests;

import io.appium.java_client.AppiumBy;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100Screen;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.*;

public class Asp100Tests {

    static CloseableAndroidDriver driver;

    @BeforeAll
    static void setup() {
        try {
            driver = DriverBuilder.getAndroidDriver();
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + BalluAsp100Screen.DEVICE_NAME + "\"));")).click(), "DeviceConnectionViewModel", "deviceType=69"
            );
            Assertions.assertTrue(logsFound);
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
                () -> driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100Screen.FAN_MODE_BUTTON)).click(), "DeviceUtils", "mode=05"
        );
        Assertions.assertTrue(logsFound);
    }
}
