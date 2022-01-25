package com.morez.app.model;

import com.morez.app.enums.DayType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PeakTime {
    private LocalTime startTime;
    private LocalTime endTime;
    private DayType dayType;
}
