package org.example.ui_utils;

import io.appium.java_client.android.AndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.log_utils.LogReader;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class PushButton {
    public static void main(String[] args) throws Exception {

        // TODO: возможно тут стоить сделать улучшенный поиск по селектору

//        AndroidDriver driver = DriverBuilder.getAndroidDriver();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                LogReader logReader = new LogReader(10, 6000);
//                System.out.println(logReader.checkLogsByFilter("AddDeviceViewModel"));
//            }
//        }).start();
//
//        try {
//            driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.hommyn.app:id/btnAction\"]")).click();
//        } finally {
//            driver.quit();
//        }
//
////        System.out.println(logReader.checkLogsByFilter());
    }
}
