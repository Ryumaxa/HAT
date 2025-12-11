package org.example;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;
import org.example.ui_utils.service.UiService;

import java.util.ArrayList;

public class Sandbox {
    public static void main(String[] args) throws Exception {
//	    UiService uiService = new UiService();
//	    ArrayList<DeviceUi> deviceUis = uiService.getDevicesUi("src/main/resources/devices.json", "src/main/resources/ui_jsons");
		
//	    for (DeviceUi deviceUi : deviceUis) {
//		    System.out.println("|-|-|-|-|-|-|-|------- " + deviceUi.getDeviceName() + " -------|-|-|-|-|-|-|-|");
//			for (MiddleElement e : deviceUi.getMiddleElements()) {
//				System.out.println(e);
//			}
//	    }
	    
	    CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();
	    System.out.println(driver.select("new UiSelector().text(\"Звук\")").getAttribute("checked"));
		driver.select("new UiSelector().text(\"Звук\")").click();
	    System.out.println(driver.select("new UiSelector().text(\"Звук\")").getAttribute("checked"));
	    driver.select("new UiSelector().text(\"Звук\")").click();
	    System.out.println(driver.select("new UiSelector().text(\"Звук\")").getAttribute("checked"));
	    driver.select("new UiSelector().text(\"Звук\")").click();
	    System.out.println(driver.select("new UiSelector().text(\"Звук\")").getAttribute("checked"));
	    driver.select("new UiSelector().text(\"Звук\")").click();
		
    }
}
