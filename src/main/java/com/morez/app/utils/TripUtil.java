package com.morez.app.utils;

import com.morez.app.config.DataLoader;
import com.morez.app.constants.CardConstants;
import com.morez.app.enums.Day;
import com.morez.app.enums.DayType;
import com.morez.app.model.Trip;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class TripUtil {

    public boolean isWeekend(Day day) {
        return Arrays.stream(CardConstants.WEEKEND)
                .filter(f-> f.equals(day.name()))
                .count() == 1;
    }

    public boolean isPeakTime(LocalTime time, Day day) {
        boolean weekend = isWeekend(day);
        DayType type = weekend ? DayType.WEEKEND : DayType.WEEKDAY;

        return DataLoader.getPeakTimings()
                .stream()
                .filter(s -> s.getStartTime().isBefore(time)
                        && s.getEndTime().isAfter(time)
                        && s.getDayType() == type)
                .count() == 1;
    }

    public boolean isSameDateTrips(List<Trip> trips) {
        long count = trips.stream().map(Trip::getDate).distinct().count();
        return count == 1;
    }

    public Integer calculateFare(int totalExpense, Integer peakTimeFare, Integer unit) {
        return totalExpense + peakTimeFare > unit ? unit - totalExpense : peakTimeFare;
    }
}
