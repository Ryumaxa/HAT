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
	    ElementsCreator elementsCreator = new ElementsCreator();
	    CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();
		
		
	    
    }
}
