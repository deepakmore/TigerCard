package com.morez.app.calculations;

import com.morez.app.model.Trip;
import com.morez.app.utils.TripUtil;
import lombok.Data;

import java.util.List;

@Data
public class DailyFareStrategy extends AbstractFareStrategy {

    IFareStrategy strategy = null;

    @Override
    public void calculate(List<Trip> trips) {
        if(TripUtil.isSameDateTrips(trips)) {
            processTrips(trips);
        } else {
            strategy.calculate(trips);
        }
    }

    @Override
    public void setNextFareStrategy(IFareStrategy fareStrategy) {
        strategy = fareStrategy;
    }
}