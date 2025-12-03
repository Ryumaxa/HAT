package org.example;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.launch_utils.DriverBuilder;
import org.example.test_running.elements_test_classes.ModeButtonTest;
import org.example.test_running.elements_test_classes.SliderTest;
import org.example.test_running.elements_test_classes.TestHelper;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.DeviceUi;
import org.example.ui_utils.elements_classes.layout_elements.BottomElement;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;
import org.example.ui_utils.service.UiService;

import java.util.ArrayList;

public class Sandbox {
    public static void main(String[] args) throws Exception {
	    UiService uiService = new UiService();
	    ArrayList<DeviceUi> deviceUis = uiService.getDevicesUi("src/main/resources/devices.json", "src/main/resources/ui_jsons");
	    
	    CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();
	    
	    ElementsCreator elementsCreator = new ElementsCreator();
	    ModeButtonTest modeButtonTest = new ModeButtonTest(driver, elementsCreator);
	    SliderTest sliderTest = new SliderTest(driver, elementsCreator);
	    TestHelper testHelper = new TestHelper(driver, elementsCreator);
	    
	    for (int i = 0; i < 1; i++) {
		    
		    for (DeviceUi deviceUi : deviceUis) {
			    if (testHelper.openDeviceCard(deviceUi.getDeviceName(), deviceUi.getType())) {
				    System.out.println("----------ТЕСТИРОВАНИЕ КНОПОК РЕЖИМОВ----------");
				    for (BottomElement element : deviceUi.getBottomElements()) {
					    System.out.println(element);
				    }
				    System.out.println("----------ТЕСТИРОВАНИЕ СЛАЙДЕРОВ----------");
				    for (MiddleElement element : deviceUi.getMiddleElements()) {
					    System.out.println(element);
				    }
			    }
		    }
	    }
    }
}
