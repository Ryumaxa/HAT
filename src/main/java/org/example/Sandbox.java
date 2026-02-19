package org.example;

import io.appium.java_client.AppiumBy;
import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;
import org.example.ui_utils.service.UiService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Objects;

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
		
		WebElement slider = driver.select("new UiSelector().resourceId(\"com.hommyn.app:id/slider\").instance(0)");
		WebElement point = slider.findElement(AppiumBy.className("android.widget.SeekBar"));
		WebElement parent = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.hommyn.app:id/tvTitle\" and @text=\"Разница температур в режиме Eco\"]/.."));
		
		point.sendKeys("0.0");
		point.click();
		double min = Double.parseDouble(Objects.requireNonNull(point.getAttribute("content-desc")).replace("Value, ", ""));
		point.sendKeys("1.0");
		point.click();
		double max = Double.parseDouble(Objects.requireNonNull(point.getAttribute("content-desc")).replace("Value, ", ""));
		
		double progressValue = 0.0;
		double displayedValue;
		
		double range = max - min;
		double step = 1.0 / range;
		double realStep = step * range;
		int numOfSteps = 1 + (int) range;
		
		for (int i = 0; i < numOfSteps; i++) {
			point.sendKeys(String.valueOf(progressValue));
			point.click();
			displayedValue = Double.parseDouble(parent.findElement(By.id("com.hommyn.app:id/tvSelectedValue")).getText());
			System.out.println((progressValue * realStep / step + min) + " " + displayedValue);
			progressValue += step;
			// TODO: проверка равенства с округлением
			if ((progressValue * realStep / step + min) != displayedValue) {
				System.out.println("Некорректные значения ползунка");
				break;
			}
		}
	}
}