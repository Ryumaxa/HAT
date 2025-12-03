package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Класс, реализующий тесты слайдеров разных типов
 */
public class SliderTest implements ElementTest<MiddleElement> {
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	private final ElementFilter filter;
	private final TestHelper helper;
	private final SliderTypes sliderType;
	// TODO: в конструкторе инициализировать тип слайдера, передавая на вход лист из всех слайдеров на основе фильтра
	
	
	public SliderTest(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
		filter = new ElementFilter();
		helper = new TestHelper(driver, elementsCreator);
	}
	
	@Override
	public void run(MiddleElement element) {
		if (filter.isSlider(element)) {
			try {
				// TODO: условия
				if (leftSliderMaxLimitCorrect(element) && leftSliderMinLimitCorrect(element)) {
					helper.printGreen(element.getType() + "_" + element.getName() + " : отработала корректно");
				} else {
					System.err.println(element.getName() + " : ошибка при проверке логов!");
				}
			} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
				System.err.println(element.getName() + " : элемент не найден!");
			}
		}
	}
	
	private boolean isAspTypeSlider(MiddleElement element) {
		// TODO: проверка, к какому типу относится слайдер
		return false;
	}
	
	// Слайдеры типа ASP-100 (ПОМОЙНЫЕ)
	private boolean leftSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.leftSliderMax(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "speed=" + element.getLimit().getMax()
		);
	}
	private boolean leftSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.leftSliderMin(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "speed=" + element.getLimit().getMin()
		);
	}
	private boolean rightSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.rightSliderMax(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "speed=" + element.getLimit().getMax()
		);
	}
	private boolean rightSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.rightSliderMax(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "speed=" + element.getLimit().getMin()
		);
	}
	
	private enum SliderTypes {
		VENTILATION, CONVECTOR, BOILER
	}
	
}
