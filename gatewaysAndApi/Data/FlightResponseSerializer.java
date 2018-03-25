package Data;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class FlightResponseSerializer implements JsonSerializer<FlightsResponse> {
	
	
    public JsonElement serialize(final FlightsResponse resp, final Type type, final JsonSerializationContext context) {
    	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        JsonObject result = new JsonObject();
        
        for(int i = 0 ; i < resp.getResults().length ; i++) {
        result.add("Airline", new JsonPrimitive(resp.getResults()[i].getAirline()));
        result.add("Destination", new JsonPrimitive(resp.getResults()[i].getDestination()));
        result.add("Departure Date ", new JsonPrimitive(df.format(resp.getResults()[i].getDepartureDate())));
        result.add("Return Date", new JsonPrimitive(df.format(resp.getResults()[i].getReturnDate())));
        result.add("The total price : $ ", new JsonPrimitive(resp.getResults()[i].getPrice()));
        result.add("Destination", new JsonPrimitive(resp.getResults()[i].getDestination()));
        }
        return result;
    }
	}
