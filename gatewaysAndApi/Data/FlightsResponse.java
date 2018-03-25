package Data;

import java.util.Arrays;

public class FlightsResponse {

	private String m_Origin;

	private String m_Currency;

	private Ticket[] m_Results;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlightsResponse Origin=" + m_Origin + ", m_Currency=" + m_Currency + ", m_Results="
				+ Arrays.toString(m_Results) + "]";
	}

	public String getOrigin() {
		return m_Origin;
	}

	public void setOrigin(String m_Origin) {
		this.m_Origin = m_Origin;
	}

	public String getCurrency() {
		return m_Currency;
	}

	public void setCurrency(String m_Currency) {
		this.m_Currency = m_Currency;
	}

	public Ticket[] getResults() {
		return m_Results;
	}

	public void setResults(Ticket[] m_Results) {
		this.m_Results = m_Results;
	}

	public void printResults(FlightsResponse rep) {
		for (int i = 0; i < rep.getResults().length; i++) {
			System.out.println(rep.getOrigin());
			System.out.println(rep.getResults()[i].getAirline());
			System.out.println(rep.getResults()[i].getDestination());
			System.out.println(rep.getResults()[i].getPrice());
			System.out.println(rep.getResults()[i].getDepartureDate());
			System.out.println(rep.getResults()[i].getReturnDate());
		}
	}


}
