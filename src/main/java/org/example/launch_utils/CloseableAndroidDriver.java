package org.example.launch_utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.example.screen_elements.device_list.DeviceListScreen;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    public WebElement selectById(String elementId) {
        return this.findElement(AppiumBy.id(elementId));
    }

    public void leftSliderMin(String leftSlider) {
        WebElement slider = this.select(leftSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, -size.getWidth() / 3, size.getHeight() / 4)
                .click()
                .perform();
    }

    public void leftSliderMid(String leftSlider) {
        WebElement slider = this.select(leftSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, size.getWidth() / 3, 0)
                .click()
                .perform();
    }

    public void leftSliderMax(String leftSlider) {
        WebElement slider = this.select(leftSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, -size.getWidth() / 3, -size.getHeight() / 4)
                .click()
                .perform();
    }

    public void rightSliderMin(String rightSlider) {
        WebElement slider = this.select(rightSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, size.getWidth() / 3, size.getHeight() / 4)
                .click()
                .perform();
    }

    public void rightSliderMid(String rightSlider) {
        WebElement slider = this.select(rightSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, -size.getWidth() / 3, 0)
                .click()
                .perform();
    }

    public void rightSliderMax(String rightSlider) {
        WebElement slider = this.select(rightSlider);
        Dimension size = slider.getSize();
        new Actions(this)
                .moveToElement(slider, size.getWidth() / 3, -size.getHeight() / 4)
                .click()
                .perform();
    }

    public void backToDeviceList() {
        for (int i = 0; i < 5; i++) {
            try {
                this.select(DeviceListScreen.VIEW_CHANGE_BUTTON);
            } catch (Exception e) {
                this.navigate().back();
            }
        }
    }
}
