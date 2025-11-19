package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.service.local.AppiumServerAvailabilityChecker;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.test_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.UiParser;
import org.example.ui_utils.elements_classes.BottomButton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.TableHeaderUI;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Sandbox {
    public static void main(String[] args) throws Exception {
        CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();

        driver.select(BalluAsp100MainScreen.SETTING_FILTER_RESOURCE).click();
        driver.select("new UiSelector().resourceId(\"android:id/button1\")").click();
        boolean isReset = LogChecker.checkLogsInBackground(
                () -> driver.select("new UiSelector().resourceId(\"android:id/button1\")").click(), "UdpConnection"//, "CmdExpendables", "value=[0]"
        );
        System.out.println(isReset);
    }
}
