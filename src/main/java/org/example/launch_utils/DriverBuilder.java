package org.example.launch_utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс-билдер для андроид-драйвера
 */
public class DriverBuilder {

    public static CloseableAndroidDriver getAndroidDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("platformName", "Android");
//        caps.setCapability("appium:deviceName", "R58T12K1HJH");
            caps.setCapability("appium:appPackage", "com.hommyn.app");
            caps.setCapability("appium:appActivity", "com.syncleoiot.app.ui.MainActivity");
            caps.setCapability("appium:noReset", "true");
            caps.setCapability("appium:automationName", "UiAutomator2");
            // TODO: подобрать параметры для корректного запуса MainActivity без сброса приложения (пока запускать вручную)

            var uri = new java.net.URI("http://0.0.0.0:4723").toURL();
            return new CloseableAndroidDriver(uri, caps);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Android driver, check Appium Server", e);
        }
    }
}
