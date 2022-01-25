package com.morez.app.calculations;

import com.morez.app.model.Trip;
import java.util.List;

public interface IFareStrategy {
    void calculate(List<Trip> trips);
    void setNextFareStrategy(IFareStrategy fareStrategy);
}
