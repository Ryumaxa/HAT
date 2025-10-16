package org.example.devices_control_via_udp_tests;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.test_utils.LogChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Asp100Tests {

    @BeforeEach
    void setup() {
        // TODO: открытие нужной активити перед тестом
    }

    @Test
    void whenAsp100CardClicked_shouldOpenAsp100Card() {
        try (CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver()) {
            boolean logsFound = LogChecker.checkLogsInBackground(
                    () -> driver.findElement(By.id("btnAction")).click(), "AddDeviceViewModel"
            );
            Assertions.assertTrue(logsFound, "Логи не содержат информации об открытом экране \"Добавить устройство\"");
        }
    }
}
