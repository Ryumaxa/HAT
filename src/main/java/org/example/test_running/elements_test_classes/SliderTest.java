package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.log_utils.LogChecker;
import org.example.ui_utils.ElementsCreator;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
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
	private SliderTypes sliderType;
	// TODO: решить проблему со слайдерами конвектора (В JSON они дублируются для всех диапазонов)
	// TODO: для конвектора надо брать левый и правый по имени, а не по индексу, т.к. их много
	
	
	public SliderTest(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
		filter = new ElementFilter();
		helper = new TestHelper(driver, elementsCreator);
	}
	
	public void determineSliderType(ArrayList<MiddleElement> middleElements) {
		middleElements.removeIf(el -> !el.getType().equals("SLIDER"));
		if (middleElements.size() == 1) {
			sliderType = SliderTypes.BOILER;
		} else if (middleElements.getFirst().getName().equals("SPEED")) {
			sliderType = SliderTypes.VENTILATION;
		} else {
			sliderType = SliderTypes.CONVECTOR;
		}
	}
	
	@Override
	public void run(MiddleElement element) {
		switch (sliderType) {
			case VENTILATION -> testVentilationSliders(element);
		}
	}
	
	private void testVentilationSliders(MiddleElement element) {
		if (filter.isSlider(element)) {
			if (element.getName().equals("SPEED")) {
				try {
					if (ventSpeedSliderMaxLimitCorrect(element) && ventSpeedSliderMinLimitCorrect(element)) {
						helper.printGreen(element.getType() + "_" + element.getName() + " : отработал корректно");
					} else {
						System.err.println(element.getName() + " : ошибка при проверке логов!");
					}
				} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
					System.err.println(element.getName() + " : элемент не найден!");
				}
			} else {
				try {
					if (ventTempSliderMaxLimitCorrect(element) && ventTempSliderMinLimitCorrect(element)) {
						helper.printGreen(element.getType() + "_" + element.getName() + " : отработал корректно");
					} else {
						System.err.println(element.getName() + " : ошибка при проверке логов!");
					}
				} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
					System.err.println(element.getName() + " : элемент не найден!");
				}
			}
		}
		// TODO: сократить позже
	}
	
	private void testConvectorSliders(MiddleElement element) {
		if (filter.isSlider(element)) {
			if (element.getName().equals("TEMPERATURE")) {
				try {
					if (convectorTempSliderMaxLimitCorrect(element) && convectorTempSliderMinLimitCorrect(element)) {
						helper.printGreen(element.getType() + "_" + element.getName() + " : отработал корректно");
					} else {
						System.err.println(element.getName() + " : ошибка при проверке логов!");
					}
				} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
					System.err.println(element.getName() + " : элемент не найден!");
				}
			} else {
				try {
					if (convectorPowerSliderMaxLimitCorrect(element) && convectorPowerSliderMinLimitCorrect(element)) {
						helper.printGreen(element.getType() + "_" + element.getName() + " : отработал корректно");
					} else {
						System.err.println(element.getName() + " : ошибка при проверке логов!");
					}
				} catch (NoSuchElementException | ExecutionException | InterruptedException | TimeoutException e) {
					System.err.println(element.getName() + " : элемент не найден!");
				}
			}
		}
		// TODO: сократить позже
	}
	
	// Слайдеры типа VENTILATION
	private boolean ventSpeedSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventLeftSliderMax(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "speed=" + element.getLimit().getMax()
		);
	}
	private boolean ventSpeedSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventLeftSliderMin(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "speed=" + element.getLimit().getMin()
		);
	}
	private boolean ventTempSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventRightSliderMax(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "temperature=" + element.getLimit().getMax() * 1.0
		);
	}
	private boolean ventTempSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventRightSliderMin(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "temperature=" + element.getLimit().getMin() * 1.0
		);
	}
	
	// Слайдеры типа CONVECTOR
	private boolean convectorTempSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventLeftSliderMax(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "temperature=" + element.getLimit().getMax() * 1.0
		);
	}
	private boolean convectorTempSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventLeftSliderMin(elementsCreator.createSliderWithIndex(0)), "DeviceUtils", "temperature=" + element.getLimit().getMin() * 1.0
		);
	}
	private boolean convectorPowerSliderMaxLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventRightSliderMax(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "speed=" + element.getLimit().getMax()
		);
	}
	private boolean convectorPowerSliderMinLimitCorrect(MiddleElement element) throws ExecutionException, InterruptedException, TimeoutException {
		return LogChecker.checkLogsInBackground(
				() -> driver.ventRightSliderMin(elementsCreator.createSliderWithIndex(1)), "DeviceUtils", "speed=" + element.getLimit().getMin()
		);
	}
	
	private enum SliderTypes {
		VENTILATION, CONVECTOR, BOILER
	}
	
}
