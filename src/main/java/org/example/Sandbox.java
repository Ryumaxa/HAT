package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100Screen;

public class Sandbox {
    public static void main(String[] args) throws Exception {

        final String DEVICE_NAME = "ASP100 TEST";
        final String CARD_XPATH = String.format("//*[@text='%s']", DEVICE_NAME);

        AndroidDriver driver = DriverBuilder.getAndroidDriver();


        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + DEVICE_NAME + "\"));")).click();
    }


}
