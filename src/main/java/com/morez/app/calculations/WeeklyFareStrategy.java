package com.morez.app.calculations;

import com.morez.app.enums.Day;
import com.morez.app.model.Trip;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Data
public class WeeklyFareStrategy extends AbstractFareStrategy {

    IFareStrategy strategy= null;
    static int weeklyTripExpense = 0;

    @Override
    public void calculate(List<Trip> trips) {

        Map<LocalDate, List<Trip>> dateWiseTrips = new TreeMap<>(trips.stream()
                    .collect(Collectors.groupingBy(Trip::getDate)));

        dateWiseTrips.entrySet().stream()
                .peek(dateWiseTrip -> {
                    if(dateWiseTrip.getValue().get(0).getDay() == Day.MONDAY)
                        weeklyTripExpense = 0;
                    weeklyTripExpense = processDayWiseTrips(dateWiseTrip.getValue(), weeklyTripExpense);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private int processDayWiseTrips(List<Trip> trips, int weeklyTripExpense) {
        return processTrips(trips, weeklyTripExpense);
    }

    @Override
    public void setNextFareStrategy(IFareStrategy fareStrategy) {
        strategy = null;
    }
}
