package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;

import Data.AggregationMode;
import Data.FlightResponseSerializer;
import Data.FlightsResponse;
import Gateways.IFlightsGateway;
import HttpGateways.FlightGateway;
import boot.AppConfig;

public class Server {

	// Sockets
	private ServerSocket server;
	private Socket clientSocket;
	private AppConfig config;

	// Streams

	// Variables
	FlightsResponse rep;
	private boolean isStopped;
	IFlightsGateway flightsGateway;
	FlightsResponse resp;
	FlightResponseSerializer ser;

	// Request Vars
	private String origin = "IL";
	private String dest = null;
	private Date departureDate = null;
	private Date returnDate = null;
	private boolean oneWay = false;
	private Integer duration = null;
	private boolean direct = true;
	private Integer maxPrice = null;

	private HashMap<String, String> map = new HashMap<String, String>();

	public Server() throws FileNotFoundException {
		this.config = new AppConfig("./app_config.xml");
		flightsGateway = new FlightGateway(config);
		map.put("Hong Kong", "HKG");
		map.put("Singapore", "SIN");
		map.put("Bangkok", "BKK");
		map.put("London", "LON");
		map.put("Kuala Lumpur", "KUL");
		map.put("Shenzhen", "SZX");
		map.put("New York City", "NYC");
		map.put("Antalya", "AYT");
		map.put("Paris", "PAR");
		map.put("Istanbul", "IST");
		map.put("Rome", "ROM");
		map.put("Dubai", "DXB");
		map.put("Guangzhou", "CAN"); // MECCA MACCUA TAIPAI CITY
		map.put("Pattaya", "PYX");
		map.put("Prague", "PRG");
		map.put("Shanghai", "SHA");
		map.put("Miami", "MIA");
		map.put("Barcelona", "BCN");
		map.put("Moscow", "MOW");
		map.put("Beijing", "BJS");
		map.put("Los Angeles", "LAX");
		map.put("Budapest", "BUD");
		map.put("Vienna", "VIE");
		map.put("Amsterdam", "AMS");
		map.put("Sofia", "SOF");
		map.put("Madrid", "MAD");
		map.put("Orlando", "ORL");
		map.put("Lima", "LIM");
		map.put("Berlin", "BER");
		map.put("Tokyo", "TYO");
		map.put("Warsaw", "WAW");
		map.put("San Francisco", "SFO");
		map.put("Buenos Aires", "BUE");
		map.put("Mexico City", "MEX");
		map.put("Mumbai", "BOM");
		map.put("Delhi", "DEL");
		map.put("Toronto", "YTZ");
		map.put("Burgas", "BOJ");
		map.put("Sydney", "SYD");
		map.put("Munich", "MUC");
		map.put("Cancun", "CUM");
		map.put("Bucharest", "BUH");
		map.put("Chiang Mai", "CNX");
		map.put("Honolulu", "HNL");
		map.put("Vancouver", "YVR");
		map.put("Kiev", "KBP");
		map.put("Rio de Janeiro", "RIO");
		map.put("Melbourne", "MEL");
		map.put("Washington D.C.", "BWI");
	}

	public void start() throws IOException, ParseException {

		server = new ServerSocket(3000);

		while (!isStopped) {
			clientSocket = server.accept();
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

						dest = in.readLine();
						System.out.println(dest);
						resp = flightsGateway.FlightInspiration("NYC", map.get(dest), departureDate, returnDate, oneWay,
								duration, direct, maxPrice, AggregationMode.DESTINATION);

						Gson gson = new Gson();
						out.write(gson.toJson(resp));

						out.close();
						in.close();

						// Print to check output.
						// for (int i = 0; i < resp.getResults().length; i++) {
						// System.out.println("Airline: " +
						// resp.getResults()[i].getAirline());
						// System.out.println("Destination: " +
						// resp.getResults()[i].getDestination());
						// System.out.println("Price: $" +
						// resp.getResults()[i].getPrice());
						// System.out.println("Departure Date: " +
						// resp.getResults()[i].getDepartureDate());
						// System.out.println("Return Date : " +
						// resp.getResults()[i].getReturnDate());
						// System.out.println("\n\n\n");
						// }
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					} finally {

						if (clientSocket != null)
							try {
								clientSocket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}

				}
			});
			t.start();
		}

	}

	public void stop() {
		isStopped = true;

	}
}
