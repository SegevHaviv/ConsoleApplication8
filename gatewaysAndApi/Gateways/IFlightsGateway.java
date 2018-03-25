package Gateways;

import Data.AggregationMode;
import Data.FlightsResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public interface IFlightsGateway {
    FlightsResponse FlightInspiration(String origin, String destination, Date fromDepartureDate, Date untilDepartureDate, Boolean oneWay, Integer duration, Boolean direct, Integer maxPrice, AggregationMode aggregationMode) throws IOException, ParseException;
}
