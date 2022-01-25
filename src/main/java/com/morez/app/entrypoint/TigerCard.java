package com.morez.app.entrypoint;

import com.morez.app.calculations.DailyFareStrategy;
import com.morez.app.calculations.IFareStrategy;
import com.morez.app.calculations.WeeklyFareStrategy;
import com.morez.app.model.Trip;
import com.morez.app.validation.UserInputValidator;
import lombok.Data;

import java.util.List;

@Data
public class TigerCard {

    private IFareStrategy fareStrategy;
    final private UserInputValidator validator;
    final private IFareStrategy dailyStrategy;
    final private IFareStrategy weeklyStrategy;

    public TigerCard() {
        validator = UserInputValidator.createInstance();
        dailyStrategy = new DailyFareStrategy();
        weeklyStrategy = new WeeklyFareStrategy();

        buildStrategies();
    }

    protected void buildStrategies() {
        dailyStrategy.setNextFareStrategy(weeklyStrategy);
        weeklyStrategy.setNextFareStrategy(null);
    }

    public void validateAndSetFinalFare(List<Trip> trips) {
        validateUserInputs(trips);
        finalCalculation(trips);
    }

    private void validateUserInputs(List<Trip> trips) {
        validator.validate(trips);
    }

    private void finalCalculation(List<Trip> trips) {
        dailyStrategy.calculate(trips);
    }
}
