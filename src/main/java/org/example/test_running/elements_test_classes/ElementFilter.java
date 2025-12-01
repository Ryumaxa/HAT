package org.example.test_running.elements_test_classes;

import org.example.ui_utils.elements_classes.layout_elements.BottomElement;

public class ElementFilter {
	
	public boolean isModeButton(BottomElement bottomElement) {
		return bottomElement.getMode() != -1;
	}
	
}
