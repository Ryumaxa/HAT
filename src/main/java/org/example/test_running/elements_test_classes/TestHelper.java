package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.screen_elements.ballu_asp100.BalluAsp100MainScreen;
import org.example.ui_utils.ElementsCreator;
import org.openqa.selenium.NoSuchElementException;

public class TestHelper {
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	
	public TestHelper(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
	}
	
	public void OpenDeviceCard(String name, int type) {
		
		try {
			boolean isCardOpened = LogChecker.checkLogsInBackground(
					() -> driver.select(elementsCreator.scrollToElementWithText(name)).click(), "DeviceConnectionViewModel", "deviceType=" + type
			);
			if (!isCardOpened) {
				System.err.println(name + " : не удалось открыть карточку устройства!");
			} else {
				this.printGreen(name + " : карточка устройства успешно открыта");
			}
		} catch (NoSuchElementException e) {
			System.err.println(name + " : карточка устройства с таким именем не найдена!");
		}
		
	}
	
	public void printGreen(String s) {
		System.out.println("\u001B[32m" + s + "\u001B[0m");
	}
	
	
	// TODO: сюда бефоры и афтеры
}
