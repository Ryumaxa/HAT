package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.layout_elements.BottomElement;
import org.openqa.selenium.NoSuchElementException;

public class ModeButtonTest implements ElementTest<BottomElement> {
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	private final ElementFilter filter;
	private final TestHelper helper;
	
	public ModeButtonTest(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
		filter = new ElementFilter();
		helper = new TestHelper(driver, elementsCreator);
	}
	
	@Override
	public void run(BottomElement element) {
		if (filter.isModeButton(element)) {
			try {
				boolean logsFound = LogChecker.checkLogsInBackground(
						() -> driver.select(elementsCreator.createButtonWithIndex(element.getIndex())).click(), "DeviceUtils", "mode=0" + element.getMode()
				);
				if (!logsFound) {
					System.err.println(element.getName() + " : ошибка при проверке логов!");
				} else {
					helper.printGreen(element.getType() + "_" + element.getName() + " : отработала корректно");
				}
			} catch (NoSuchElementException e) {
				System.err.println(element.getName() + " : элемент не найден!");
			}
		}
	}
}
