package com.morez.app.utils;

import com.google.common.collect.Lists;
import com.morez.app.enums.Day;
import com.morez.app.model.Trip;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TripUtilTest {

    @Test
    public void shouldReturnTrueIfItIsWeekend() {
        assertTrue(TripUtil.isWeekend(Day.SATURDAY));
        assertTrue(TripUtil.isWeekend(Day.SUNDAY));
    }

    @Test
    public void shouldReturnFalseIfItIsWeekend() {
        assertFalse(TripUtil.isWeekend(Day.MONDAY));
        assertFalse(TripUtil.isWeekend(Day.WEDNESDAY));
    }

    @Test
    public void shouldTrueIfItIsPeakTime() {
        assertTrue(TripUtil.isPeakTime(LocalTime.parse("07:15"), Day.WEDNESDAY));
        assertTrue(TripUtil.isPeakTime(LocalTime.parse("09:15"), Day.SATURDAY));
    }

    @Test
    public void shouldFalseIfItIsPeakTime() {
        assertFalse(TripUtil.isPeakTime(LocalTime.parse("11:15"), Day.WEDNESDAY));
        assertFalse(TripUtil.isPeakTime(LocalTime.parse("08:15"), Day.SATURDAY));
    }

    @Test
    public void shouldReturnTrueIfDateIsSame() {
        assertTrue(TripUtil.isSameDateTrips(getSameDateTrips()));
    }

    @Test
    public void shouldReturnFalseIfDateIsNotSame() {
        List<Trip> trips = getSameDateTrips();
        trips.add(new Trip(LocalDate.now().plusDays(1), Day.WEDNESDAY, LocalTime.now(), "1", "2"));

        assertFalse(TripUtil.isSameDateTrips(trips));
    }

    @Test
    public void shouldReturnPeakTimeFare() {
        int unit=650, totalExpense=500, peakTimeFare=100;
        Integer actualFare = TripUtil.calculateFare(totalExpense, peakTimeFare, unit);
        assertEquals(peakTimeFare, actualFare.intValue());
    }

    @Test
    public void shouldReturnDifferenceOfUnitAndTotalExpense() {
        int unit=570, totalExpense=550, peakTimeFare=100, expectedFare = 20;
        Integer actualFare = TripUtil.calculateFare(totalExpense, peakTimeFare, unit);
        assertEquals(expectedFare, actualFare.intValue());
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
