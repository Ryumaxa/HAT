package org.example.hommyn_common_tests;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import io.appium.java_client.*;

class DeviceListScreenTests {

    @BeforeEach
    void setUp()  {
        // TODO: открытие нужной активити перед тестом
    }

    @Test
    void whenAddDeviceButtonClicked_shouldOpenAddDeviceViewModel() {
        try (CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver()) {
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(By.id("btnAction")).click(), "AddDeviceViewModel"
                    // TODO: заменить на Appium селекторы
            );
            Assertions.assertTrue(logsFound, "Логи не содержат информации об открытом экране \"Добавить устройство\"");
        }
    }
}