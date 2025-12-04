package org.example.test_running;

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

import java.io.IOException;
import java.util.ArrayList;

/**
 * Основной класс для запуска тестов
 */
public class TestRunner {

	public static void main(String[] args) throws IOException {
		UiService uiService = new UiService();
		ArrayList<DeviceUi> deviceUis = uiService.getDevicesUi("src/main/resources/devices.json", "src/main/resources/ui_jsons");
		
		CloseableAndroidDriver driver = DriverBuilder.getAndroidDriver();
		
		ElementsCreator elementsCreator = new ElementsCreator();
		ModeButtonTest modeButtonTest = new ModeButtonTest(driver, elementsCreator);
		SliderTest sliderTest = new SliderTest(driver, elementsCreator);
		TestHelper testHelper = new TestHelper(driver, elementsCreator);
		
//		for (DeviceUi deviceUi : deviceUis) {
//			System.out.println(deviceUi);
//		}
		
		for (int i = 0; i < 1; i++) {

			for (DeviceUi deviceUi : deviceUis) {
				System.out.println("|-|-|-|-|-|-|-|------- " + deviceUi.getDeviceName() + " -------|-|-|-|-|-|-|-|");
				if (testHelper.openDeviceCard(deviceUi.getDeviceName(), deviceUi.getType())) {
					System.out.println("----------ТЕСТИРОВАНИЕ КНОПОК РЕЖИМОВ----------");
//					for (BottomElement element : deviceUi.getBottomElements()) {
//						modeButtonTest.run(element);
//					}
					System.out.println("----------ТЕСТИРОВАНИЕ СЛАЙДЕРОВ----------");
					sliderTest.determineSliderType(deviceUi.getMiddleElements());
					for (MiddleElement element : deviceUi.getMiddleElements()) {
						sliderTest.run(element);
					}
				}
			}
		}
		
		// TODO: сделать базовые тесты по типу элемента на основе входных параметров (для кнопок режимов, для ползунков, для чекбоксов и т.д.)
		// TODO: добавить в тесты рандом
		// TODO: добавить нумерацию тестов к выводу (лучше писать отчет в файл в перспективе)
		// TODO: предусмотреть читаемый формат вывода отчета
		// TODO: добавить возможность установить число итерация
		// TODO: сделать так, чтобы исключения не ломали тесты
	}
}
