package org.example.test_running.elements_test_classes;

import org.example.ui_utils.elements_classes.layout_elements.BottomElement;
import org.example.ui_utils.elements_classes.layout_elements.MiddleElement;

public class ElementFilter {
	
	public boolean isModeButton(BottomElement bottomElement) {
		return bottomElement.getMode() != -1;
	}
	
	public boolean isSlider(MiddleElement middleElement) {
		return middleElement.getType().equals("SLIDER");
	}
	
}
