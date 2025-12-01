package org.example.test_running.elements_test_classes;

import org.example.launch_utils.CloseableAndroidDriver;
import org.example.ui_utils.ElementsCreator;

public class TestHelper {
	private final CloseableAndroidDriver driver;
	private final ElementsCreator elementsCreator;
	
	public TestHelper(CloseableAndroidDriver driver, ElementsCreator elementsCreator) {
		this.driver = driver;
		this.elementsCreator = elementsCreator;
	}
	
	public void printGreen(String s) {
		System.out.println("\u001B[32m" + s + "\u001B[0m");
	}
	
	
	// TODO: сюда бефоры и афтеры
}
