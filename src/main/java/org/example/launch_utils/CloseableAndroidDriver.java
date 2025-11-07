package org.example.launch_utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;

import java.io.Closeable;
import java.net.URL;

/**
 * Обертка для AndroidDriver, чтобы не закрывать вручную
 */
public class CloseableAndroidDriver extends AndroidDriver implements Closeable {
    public CloseableAndroidDriver(URL url, Capabilities capabilities) {
        super(url, capabilities);
    }

    @Override
    public void close() {
        this.quit();
    }

    public WebElement select(String elementName) {
        return this.findElement(AppiumBy.androidUIAutomator(elementName));
    }
}
