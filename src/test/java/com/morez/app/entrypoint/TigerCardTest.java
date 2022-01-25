package com.morez.app.entrypoint;

import com.google.common.collect.Lists;
import com.morez.app.calculations.DailyFareStrategy;
import com.morez.app.calculations.IFareStrategy;
import com.morez.app.calculations.WeeklyFareStrategy;
import com.morez.app.enums.Day;
import com.morez.app.model.Trip;
import com.morez.app.validation.UserInputValidator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TigerCardTest {
    @InjectMocks TigerCard tigerCard;
    @Mock IFareStrategy fareStrategy;
    @Mock DailyFareStrategy dailyFareStrategy;
    @Mock WeeklyFareStrategy weeklyFareStrategy;
    @Mock UserInputValidator userInputValidator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldNotNull() {
        assertNotNull(dailyFareStrategy);
        assertNotNull(weeklyFareStrategy);
        assertNotNull(userInputValidator);
    }

    @Test
    @Ignore
    public void shouldValidateAndProcessTrips() {
        tigerCard.validateAndSetFinalFare(getSameDateTrips());
        verify(userInputValidator).validate(getSameDateTrips());
    }

    public List<Trip> getSameDateTrips() {
        return Lists.newArrayList(
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2"),
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2"),
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2"),
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2"),
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2")
        );
    }
}