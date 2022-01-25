package com.morez.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fare {
    // @Todo : Add annotaiton offPeakTimeFare should not be more than peakTimefAre and vice versa
    private Integer peakTimeFare;
    private Integer offPeakTimeFare;
}
