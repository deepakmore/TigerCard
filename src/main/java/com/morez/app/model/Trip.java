package com.morez.app.model;

import com.morez.app.enums.Day;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class Trip {
    final private LocalDate date;
    final private Day day;
    final private LocalTime startTime;
    final private String sourceZone;
    final private String destinationZone;
    private Integer fare = 0;

    public Trip(String [] line) {
        this.date = LocalDate.parse(line[0]);
        this.day = Day.valueOf(line[1]);
        this.startTime = LocalTime.parse(line[2]);
        this.sourceZone = line[3];
        this.destinationZone = line[4];
    }
}

