package org.example.ui_utils.elements_classes.for_parsing.ui_json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Settings {
	private String type;
	private String feature;
	private HashMap<String, String> title;
	@JsonProperty("program_data")
	private ProgramData programData;
}
