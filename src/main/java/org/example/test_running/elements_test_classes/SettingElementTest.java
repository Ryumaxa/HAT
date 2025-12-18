package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.layout_elements.SettingElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class SettingElementTest implements ElementTest<SettingElement> {
	
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	private final ElementFilter filter;
	private final TestHelper helper;
	
	public SettingElementTest(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
		filter = new ElementFilter();
		helper = new TestHelper(driver, elementsCreator);
	}
	
	@Override
	public void run(SettingElement element) {
		
		//TODO: дополнить для остальных элементов экрана настроек
		if (element.getType().equals("SWITCH")) {
			checkboxTest(element);
		}
	}

	private void checkboxTest(SettingElement element) {
		WebElement checkbox = driver.select(elementsCreator.scrollToElementWithText(element.getNameRu()));
		String attributeValue = checkbox.getAttribute("checked");
		boolean isChecked = attributeValue != null && attributeValue.equals("true");
		if (isChecked) checkbox.click();
		String udpCommandName = "Cmd" + element.getFeature().substring(0, 1).toUpperCase() + element.getFeature().substring(1);
		System.out.println(udpCommandName);
		
		try {
			boolean logsFoundOn = LogChecker.checkLogsInBackground(
					() -> checkbox.click(), "DeviceUtils", udpCommandName, "enabled=1"
			);
			boolean logsFoundOff = LogChecker.checkLogsInBackground(
					() -> checkbox.click(), "DeviceUtils", udpCommandName, "enabled=0"
			);
			
			if (!logsFoundOn && !logsFoundOff) {
				System.err.println(element.getNameEn() + " : ошибка при проверке логов!");
			} else {
				helper.printGreen(element.getType() + "_" + element.getNameEn() + " : отработал корректно");
			}
			
		} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
			System.err.println(element.getNameEn() + " : элемент не найден!");
		}
	}
}
