package com.morez.app.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.morez.app.enums.CappingFrequency;
import com.morez.app.enums.DayType;
import com.morez.app.model.Capping;
import com.morez.app.model.CappingUnitWithFare;
import com.morez.app.model.Fare;
import com.morez.app.model.PeakTime;
import com.morez.app.model.Zone;
import com.morez.app.model.ZoneRoute;
import com.morez.app.utils.KeyGenerator;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final public class DataLoader {

    @Getter private final static List<Zone> zones;
    @Getter private final static Map<Integer, ZoneRoute> routes;
    @Getter private final static List<PeakTime> peakTimings;

    static {
        zones = Collections.unmodifiableList(loadZonesFromSource());
        routes = Collections.unmodifiableMap(loadZoneRoutesAndFareFromSource());
        peakTimings = Collections.unmodifiableList(loadPeakTimingsFromSource());
    }

    protected static List<PeakTime> loadPeakTimingsFromSource() {
        return Lists.newArrayList(
                new PeakTime(LocalTime.parse("07:00"), LocalTime.parse("10:30"), DayType.WEEKDAY),
                new PeakTime(LocalTime.parse("17:00"), LocalTime.parse("20:00"), DayType.WEEKDAY),
                new PeakTime(LocalTime.parse("09:00"), LocalTime.parse("11:00"), DayType.WEEKEND),
                new PeakTime(LocalTime.parse("18:00"), LocalTime.parse("22:00"), DayType.WEEKEND)
        );
    }

    protected static Map<Integer, ZoneRoute> loadZoneRoutesAndFareFromSource() {
        Map<Integer, ZoneRoute> routes = Maps.newHashMap();
        Map<CappingFrequency, CappingUnitWithFare> cappings = Maps.newHashMap();

        // Zone - 1 to 1 Records
        cappings.put(CappingFrequency.DAILY, new CappingUnitWithFare(120, 5));
        cappings.put(CappingFrequency.WEEKLY, new CappingUnitWithFare(600, 0));

        ZoneRoute route = new ZoneRoute(
                new Zone("1"), new Zone("1"),
                new Fare(30, 25),
                new Capping(cappings));

        routes.put(KeyGenerator.generate(route.getSource().getZoneName(), route.getDestination().getZoneName()), route);

        // Zone - 1 to 2 and vice versa  Records
        cappings.put(CappingFrequency.DAILY, new CappingUnitWithFare(120, 5));
        cappings.put(CappingFrequency.WEEKLY, new CappingUnitWithFare(600, 0));

        route = new ZoneRoute(
                new Zone("1"), new Zone("2"),
                new Fare(35, 30),
                new Capping(cappings));

        routes.put(KeyGenerator.generate(route.getSource().getZoneName(), route.getDestination().getZoneName()), route);

        // Zone - 2 to 2 Records
        cappings.put(CappingFrequency.DAILY, new CappingUnitWithFare(120, 5));
        cappings.put(CappingFrequency.WEEKLY, new CappingUnitWithFare(600, 0));

        route = new ZoneRoute(
                new Zone("2"), new Zone("2"),
                new Fare(25, 20),
                new Capping(cappings));
        routes.put(KeyGenerator.generate(route.getSource().getZoneName(), route.getDestination().getZoneName()), route);
        return routes;
    }

    protected static List<Zone> loadZonesFromSource() {
        return Lists.newArrayList(new Zone("1"), new Zone("2"));
    }

    public static void main(String[] args) {
        System.out.println(loadPeakTimingsFromSource());
    }
}
