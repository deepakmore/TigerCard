package com.morez.app.model;

import com.morez.app.enums.CappingFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Capping {
    Map<CappingFrequency, CappingUnitWithFare> capPrice;
}
