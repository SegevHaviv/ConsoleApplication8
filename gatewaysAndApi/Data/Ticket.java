package Data;

import com.google.gson.JsonElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Ticket {

    private String m_Destination;

    private Date m_DepartureDate;

    private Date m_ReturnDate;

    private float m_Price;

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket [Destination=" + m_Destination + ", DepartureDate=" + m_DepartureDate + ", ReturnDate="
				+ m_ReturnDate + ", Price=" + m_Price + ", Airline=" + m_Airline + "]";
	}

	private String m_Airline;

    public Ticket(JsonElement element) throws ParseException {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	
      Set<Map.Entry<String,JsonElement>> fields = element.getAsJsonObject().entrySet();
      
        for (Map.Entry<String,JsonElement> item:fields) {
            JsonElement val=item.getValue();
            switch (item.getKey()){
                case "destination":setDestination(val.getAsString());break;
                case "departure_date":setDepartureDate(df.parse((val.getAsString())));break;
                case "return_date":setReturnDate(df.parse(val.getAsString()));break;
                case "price":setPrice(val.getAsFloat());break;
                case "airline":setAirline(val.getAsString()); break;
            }
        }
    }

    public String getDestination() {
        return m_Destination;
    }

    public void setDestination(String m_Destination) {
        this.m_Destination = m_Destination;
    }

    public Date getDepartureDate() {
        return m_DepartureDate;
    }

    public void setDepartureDate(Date string) {
        this.m_DepartureDate = string;
    }

    public Date getReturnDate() {
        return m_ReturnDate;
    }

    public void setReturnDate(Date string) {
        this.m_ReturnDate = string;
    }

    public float getPrice() {
        return m_Price;
    }

    public void setPrice(float m_Price) {
        this.m_Price = m_Price;
    }

    public String getAirline() {
        return m_Airline;
    }

    public void setAirline(String m_Airline) {
        this.m_Airline = m_Airline;
    }
}
