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

        // Ниже примеры тестов полей для ввода текста
//        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
//        Thread.sleep(50);
//        String prevRoom = driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).getText();
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("test_room");
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();
//
//        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
//        Thread.sleep(50);
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys("not_test_room");
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_CANCEL).click();
//
//        driver.select(BalluAsp100MainScreen.SETTING_DEVICE_ROOM).click();
//        Thread.sleep(50);
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_FIELD).sendKeys(prevRoom);
//        driver.select(BalluAsp100MainScreen.SETTING_INPUT_SAVE).click();

        System.out.println(driver.select("new UiSelector().text(\"" + "Продолжить" + "\")").isDisplayed());
    }
}
