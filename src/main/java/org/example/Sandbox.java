package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

// TODO: продумать взаимодействие с ползунками и стереть тут все
public class Sandbox {
    public static void main(String[] args) throws Exception {
        CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();

        WebElement leftSlider = driver.select(BalluAsp100MainScreen.SPEED_SLIDER);
        WebElement rightSlider = driver.select(BalluAsp100MainScreen.TEMPERATURE_SLIDER);

        Dimension size = leftSlider.getSize();

        // Максимальное значение левого ползунка
        new Actions(driver)
                .moveToElement(leftSlider, -size.getWidth()/3, -size.getHeight()/4)
                .click()
                .perform();

        // Минимальное значение левого ползунка
        System.out.println(size);
        new Actions(driver)
                .moveToElement(leftSlider, -size.getWidth()/3, size.getHeight()/4)
                .click()
                .perform();

        // Максимальное значение правого ползунка
        new Actions(driver)
                .moveToElement(rightSlider, size.getWidth()/3, -size.getHeight()/4)
                .click()
                .perform();

        // Минимальное значение правого ползунка
        new Actions(driver)
                .moveToElement(rightSlider, size.getWidth()/3, size.getHeight()/4)
                .click()
                .perform();

    }


}
