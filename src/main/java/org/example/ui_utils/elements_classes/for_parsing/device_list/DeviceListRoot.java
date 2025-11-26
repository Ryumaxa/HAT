package org.example.ui_utils.elements_classes.for_parsing.device_list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceListRoot {
    @JsonProperty("devices")
    private DeviceData[] devicesData;
}
