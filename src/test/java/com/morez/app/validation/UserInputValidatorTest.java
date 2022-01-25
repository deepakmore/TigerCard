package com.morez.app.validation;

import com.morez.app.config.DataLoader;
import com.morez.app.enums.Day;
import com.morez.app.model.Trip;
import com.morez.app.validation.exceptions.InvalidTimeFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserInputValidatorTest {

    @Spy
    @InjectMocks UserInputValidator validator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPassIfSourceAndDestinationZoneNameIsValid() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22:12"), "1", "1");
        validator.validate(swipe);
    }

    @Test
    public void shouldCallOnceIfSourceAndDestinationZoneNameAreSame() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22:12"), "1", "1");
        validator.validate(swipe);
        verify(validator, times(1)).validateZoneName(swipe.getSourceZone(), DataLoader.getZones());
    }

    @Test
    public void shouldCallTwiceIfSourceAndDestinationZoneNameAreDifferent() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22:12"), "1", "2");
        validator.validate(swipe);
        verify(validator, times(1)).validateZoneName(swipe.getSourceZone(), DataLoader.getZones());
    }

    @Test
    public void shouldPassIfTimeFormatIsCorrect() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22:12"), "1", "1");
        validator.validate(swipe);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailIfTimeFormatIsNotCorrect() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("10:123"), "1", "1");
        validator.validate(swipe);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailIfTimeFormatIsNotMatchesWith24HrFormat() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("24:12"), "1", "1");
        validator.validate(swipe);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailIfTimeContainsAnyAlphabet() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22:1a"), "1", "1");
        validator.validate(swipe);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldFailIfTimeContainsAnySpecialCharacterExceptColon() {
        Trip swipe = new Trip(LocalDate.now(), Day.MONDAY, LocalTime.parse("22-13"), "1", "1");
        validator.validate(swipe);
    }
}