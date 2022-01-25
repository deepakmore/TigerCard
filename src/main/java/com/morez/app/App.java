package com.morez.app;

import com.morez.app.entrypoint.TigerCard;
import com.morez.app.model.Trip;
import com.morez.app.utils.UserInputReader;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class App {

    public static void main( String[] args ) {
        initAppAndProcessTrips(args[0]);
    }

    private static void initAppAndProcessTrips(String fileName) {
        List<Trip> trips = UserInputReader.captureUserTripsFromTextFile(new File(
                Objects.requireNonNull(App.class
                        .getClassLoader()
                        .getResource(fileName))
                        .getFile()
        ));

        TigerCard card = new TigerCard();
        print(trips, false);
        System.out.println("--------------------------------");
        card.validateAndSetFinalFare(trips);
        print(trips, true);
    }

    private static void print(List<Trip> trips, boolean showOutput) {
        System.out.println("Date\t\tDay\t\tTime\tS\tD\tFare");
        System.out.println("-------------------------------------------------");
        int totalExpense = 0;

        for (Trip trip: trips) {
            System.out.println(trip.getDate()+ "\t"
                    + trip.getDay() + "\t"
                    + trip.getStartTime() + "\t"
                    + trip.getSourceZone() + "\t"
                    + trip.getDestinationZone() + "\t"
                    + trip.getFare() + "\t"
            );
            totalExpense += trip.getFare();
        }
        if(showOutput)
            System.out.println("Output - " + totalExpense);
    }
}
