package com.morez.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CappingUnitWithFare {
    Integer unit;
    Integer price;
}
