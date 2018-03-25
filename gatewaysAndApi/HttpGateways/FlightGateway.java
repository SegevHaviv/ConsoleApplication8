package HttpGateways;

import Data.*;
import Gateways.IFlightsGateway;
import boot.AppConfig;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightGateway implements IFlightsGateway {

    private String m_ApiKey;
    private String m_Url;

    public FlightGateway(AppConfig config) {

        m_ApiKey = config.getApiKey();
        m_Url = config.getBaseUrl() + "flights/";
    }

    public FlightsResponse FlightInspiration(String origin, String destination, Date fromDepartureDate, Date untilDepartureDate, Boolean oneWay, Integer duration, Boolean direct, Integer maxPrice, AggregationMode aggregationMode) throws IOException, ParseException {
        StringBuilder builder = new StringBuilder(m_Url);
        builder.append("inspiration-search?apikey=");
        builder.append(m_ApiKey);
        builder.append("&origin=" + origin);
        if (destination != null && !destination.isEmpty())
            builder.append("&destination=" + destination);

        if (fromDepartureDate != null) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            builder.append("&departure_date=" + formatter.format(fromDepartureDate));
            if (untilDepartureDate != null)
                builder.append("--" + formatter.format(untilDepartureDate));
        }

        URL url = new URL(builder.toString());
        HttpURLConnection connection = null;
        JsonReader reader = null;
        FlightsResponse result = new FlightsResponse();
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            reader = new JsonReader(new InputStreamReader((InputStream) connection.getContent()));
            JsonParser parser = new JsonParser();
            Set<Map.Entry<String, JsonElement>> resp = parser.parse(reader).getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> item : resp) {
                switch (item.getKey()) {
                    case "message":
                        break;
                    case "origin":
                        result.setOrigin(item.getValue().getAsString());
                        break;
                    case "currency":
                        result.setOrigin(item.getValue().getAsString());
                        break;
                    case "results":
                        JsonArray arr = item.getValue().getAsJsonArray();
                        ArrayList<Ticket> ticketsList = new ArrayList<>();

                        for (JsonElement jsTick : arr)
                            ticketsList.add(new Ticket(jsTick));

                        Ticket[] ticketsArr = new Ticket[ticketsList.size()];
                        ticketsList.toArray(ticketsArr);
                        result.setResults(ticketsArr);
                        break;
                }
            }
        } finally {
            if (connection != null)
                connection.disconnect();
            if (reader != null)
                reader.close();
        }


        return result;
    }

}
