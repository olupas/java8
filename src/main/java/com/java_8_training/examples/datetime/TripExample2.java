package com.java_8_training.examples.datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * .
 */
public class TripExample2 {

    public static void main(String[] args) {

        // Flight from London to Stockholm
        ZoneId london = ZoneId.of("Europe/London");
        LocalDate nov4 = LocalDate.of(2014, Month.NOVEMBER, 4);
        LocalTime early = LocalTime.parse("08:45");
        ZonedDateTime flightDeparture = ZonedDateTime.of(nov4, early, london);
        System.out.println(flightDeparture);

        LocalTime from = LocalTime.from(flightDeparture);
        System.out.println(from);

        // Arriving at the airport

        /*Duration _2hours = Duration.ofHours(2);
        LocalDateTime requiredAirportArrival = flightDeparture.minus(_2hours);
        System.out.println("I need to arrive at: " + requiredAirportArrival);
*/
        // Is the train ok?
        /*LocalDateTime trainArrival = LocalDateTime.of(nov4, LocalTime.of(6, 30));
        System.out.println("Is this train ok to take? " + trainArrival.isBefore(requiredAirportArrival));
*/
        // Arrival in Stockholm
        ZonedDateTime touchDown = ZonedDateTime.of(nov4, LocalTime.of(11, 35), ZoneId.of("Europe/Stockholm"));
        Duration flightLength = Duration.between(flightDeparture, touchDown);
        System.out.println(flightLength);

        // How long have I been in continental Europe?
        ZonedDateTime now = ZonedDateTime.now();
        Duration timeHere = Duration.between(touchDown, now);
        System.out.println(timeHere);
    }

}
