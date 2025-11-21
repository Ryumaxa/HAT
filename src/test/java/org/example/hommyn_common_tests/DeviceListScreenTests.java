package org.example.hommyn_common_tests;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.log_utils.LogChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.appium.java_client.*;

class DeviceListScreenTests {

    @BeforeEach
    void setUp()  {
        // TODO: открытие нужной активити перед тестом
    }

    @Test
    void whenAddDeviceButtonClicked_shouldOpenAddDeviceScreen() {
        try (CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver()) {
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(AppiumBy.id("btnAction")).click(), "AddDeviceViewModel"
            );
            Assertions.assertTrue(logsFound);
        }
    }
}