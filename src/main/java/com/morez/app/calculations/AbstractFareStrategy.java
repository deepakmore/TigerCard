package com.morez.app.calculations;

import com.morez.app.constants.CardConstants;
import com.morez.app.enums.CappingFrequency;
import com.morez.app.model.Trip;
import com.morez.app.model.ZoneRoute;
import com.morez.app.utils.TripUtil;
import com.morez.app.utils.ZoneRouteUtil;

import java.util.List;

public abstract class AbstractFareStrategy implements IFareStrategy {

    protected int processTrips(List<Trip> trips, int weeklyTripExpense) {
        return process(trips, weeklyTripExpense);
    }

    protected void processTrips(List<Trip> trips) {
        process(trips, 0);
    }

    private int process(List<Trip> trips, int weeklyTripExpense) {
        int dailyTripExpense = 0;
        for (Trip trip: trips) {
            final ZoneRoute zoneRoute = ZoneRouteUtil.getZoneRoute(trip.getSourceZone(), trip.getDestinationZone());
            final Integer dailyCapUnit = zoneRoute.getCapping().getCapPrice().get(CappingFrequency.DAILY).getUnit();
            final Integer weeklyCapUnit = zoneRoute.getCapping().getCapPrice().get(CappingFrequency.WEEKLY).getUnit();

            if(dailyCapUnit <= dailyTripExpense || weeklyCapUnit <= weeklyTripExpense) {
                trip.setFare(CardConstants.ZERO);
            } else if (TripUtil.isPeakTime(trip.getStartTime(), trip.getDay())) {
                trip.setFare(TripUtil.calculateFare(dailyTripExpense, zoneRoute.getFare().getPeakTimeFare(), dailyCapUnit));
            } else {
                trip.setFare(TripUtil.calculateFare(dailyTripExpense, zoneRoute.getFare().getOffPeakTimeFare(), dailyCapUnit));
            }
            dailyTripExpense += trip.getFare();
        }
        return weeklyTripExpense + dailyTripExpense;
    }
}
