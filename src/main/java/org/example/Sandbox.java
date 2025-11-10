package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class Sandbox {
    public static void main(String[] args) throws Exception {
        CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();

//        driver.executeScript("mobile: startActivity",
//                ImmutableMap.of("intent", "com.hommyn.app/com.syncleoiot.app.ui.MainActivity"));\


        String appstartPackage = "com.hommyn.app";
        String appstartActivity = "com.syncleoiot.app.ui.MainActivity";
        String intent = appstartPackage + "/" + appstartActivity;
        Map<String, Object> params = new HashMap<>();
        params.put("intent", intent);
        driver.executeScript("mobile: startActivity", params);
    }
}
