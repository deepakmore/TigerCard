package com.morez.app.validation;

import com.morez.app.config.DataLoader;
import com.morez.app.model.Trip;
import com.morez.app.model.Zone;
import com.morez.app.validation.exceptions.InvalidTimeFormatException;
import com.morez.app.validation.exceptions.ZoneNameNotFoundException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.morez.app.constants.CardConstants.INPUT_TIME_FORMAT;

public class UserInputValidator {

    private static UserInputValidator validator;

    private UserInputValidator() { }

    public static UserInputValidator createInstance() {
        if(validator == null) {
            validator = new UserInputValidator();
        }
        return validator;
    }

    public void validate(List<Trip> tripRecords) {
        tripRecords.forEach(this::validate);
    }

    public void validate(Trip tripRecord) {
        validateZoneName(tripRecord.getSourceZone(), DataLoader.getZones());
        if(!tripRecord.getSourceZone().equalsIgnoreCase(tripRecord.getDestinationZone()))
            validateZoneName(tripRecord.getDestinationZone(), DataLoader.getZones());
        //@Todo : ValidateDate
    }

    protected void validateZoneName(String expression, List<Zone> zones) {
        long count = zones.stream()
                .filter(a -> a.getZoneName().equals(expression))
                .count();
        if (count == 0)
            throw new ZoneNameNotFoundException(expression);
    }
}
