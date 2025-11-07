package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.openqa.selenium.WebElement;

public class Sandbox {
    public static void main(String[] args) throws Exception {

        final String DEVICE_NAME = "ASP100 TEST";
        final String CARD_XPATH = String.format("//*[@text='%s']", DEVICE_NAME);

        AndroidDriver driver = DriverBuilder.getAndroidDriver();

        WebElement powerButton = driver.findElement(AppiumBy.androidUIAutomator(BalluAsp100MainScreen.POWER_ON_OFF_BUTTON));

        powerButton.click();
    }


}
