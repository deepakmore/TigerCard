package com.morez.app.calculations;

import com.google.common.collect.Lists;
import com.morez.app.enums.Day;
import com.morez.app.model.Trip;
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

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DailyFareStrategyTest {

    @InjectMocks DailyFareStrategy dailyFareStrategy;
    @Mock IFareStrategy strategy;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void shouldCallProcessTrips() {
        List<Trip> trips = getAndSetTripsData();

        dailyFareStrategy.calculate(trips);

        verify(strategy, never()).calculate(trips);
    }

    List<Trip> getAndSetTripsData() {
        return Lists.newArrayList(
                new Trip(LocalDate.now(), Day.TUESDAY, LocalTime.now(), "1", "2"),
                new Trip(LocalDate.now().minusDays(1), Day.MONDAY, LocalTime.now(), "1", "2")
        );
    }
}
