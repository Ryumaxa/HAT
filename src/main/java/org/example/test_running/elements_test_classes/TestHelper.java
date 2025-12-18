package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.screen_elements.device_list.DeviceListScreen;
import org.example.ui_utils.ElementsCreator;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class TestHelper {
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	
	public TestHelper(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
	}
	
	public boolean openDeviceCard(String name, int type) {
		boolean isCardOpened = false;
		try {
			isCardOpened = LogChecker.checkLogsInBackground(
					() -> driver.select(elementsCreator.scrollToElementWithText(name)).click(), 15000, "DeviceConnectionViewModel", "deviceType=" + type
			);
			if (!isCardOpened) {
				System.err.println(name + " : не удалось открыть карточку устройства!");
			} else {
				this.printGreen(name + " : карточка устройства успешно открыта");
			}
		} catch (NoSuchElementException e) {
			System.err.println(name + " : карточка устройства с таким именем не найдена!");
		} catch (ExecutionException | InterruptedException | TimeoutException e) {
			throw new RuntimeException(e);
		}
		return isCardOpened;
	}
	
	public void backToDeviceList() {
		for (int i = 0; i < 5; i++) {
			try {
				driver.select(DeviceListScreen.VIEW_CHANGE_BUTTON);
			} catch (Exception e) {
				driver.navigate().back();
			}
		}
	}
	
	public void printGreen(String s) {
		System.out.println("\u001B[32m" + s + "\u001B[0m");
	}
	
	
	// TODO: сюда бефоры и афтеры
}
