package org.example.ui_utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.log_utils.LogReader;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class PushButton {
    public static void main(String[] args) throws Exception {

        final String DEVICE_NAME = "ASP100 TEST";
        final String CARD_XPATH = String.format("//*[@text='%s']", DEVICE_NAME);

        AndroidDriver driver = DriverBuilder.getAndroidDriver();

        try {
//            driver.findElement(AppiumBy.xpath(CARD_XPATH)).click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.hommyn.app:id/ltFeature\").instance(6)")).click();
        } finally {
            driver.quit();
        }
    }
}
