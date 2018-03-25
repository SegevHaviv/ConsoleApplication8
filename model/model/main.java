package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.io.*;
import java.net.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class main {

	public static void main(String[] args) {
		String cities = "Hong Kong,Singapore,Bangkok,London,Macau,Kuala Lumpur,Shenzhen,New York City,Antalya,Paris,Istanbul,Rome,Dubai,Guangzhou,Phuket,Mecca,Pattaya,Taipei City,Prague,Shanghai,Las Vegas,Miami,Barcelona,Moscow,Beijing,Los Angeles,Budapest,Vienna,Amsterdam,Sofia,Madrid,Orlando,Ho Chi Minh City,Lima,Berlin,Tokyo,Warsaw,Chennai,Cairo,Nairobi,Hangzhou,Milan,San Francisco,Buenos Aires,Venice,Mexico City,Dublin,Seoul,Mugla,Mumbai,Denpasar,Delhi,Toronto,Zhuhai,St Petersburg,Burgas,Sydney,Djerba,Munich,Johannesburg,Cancun,Edirne,Suzhou,Bucharest,Punta Cana,Agra,Jaipur,Brussels,Nice,Chiang Mai,Sharm el-Sheikh,Lisbon,East Province,Marrakech,Jakarta,Manama,Hanoi,Honolulu,Manila,Guilin,Auckland,Siem Reap,Sousse,Amman,Vancouver,Abu Dhabi,Kiev,Doha,Florence,Rio de Janeiro,Melbourne,Washington D.C.,Riyadh,Christchurch,Frankfurt,Baku,Sao Paulo,Harare,Kolkata,Nanjing";
		String[] cities2 = cities.split(",");
		
		String[] data = {"",
															"head",
							"Cold", 												"Hot",
				"Alone", 					"Crowded",				"Alone",				 "Crowded",
				"Passive", "Active",	"Passive", "Active",	"Passive", "Active",	"Passive", "Active",
				"C", "E",	"C", "E",	"C", "E",	"C", "E",	"C", "E",	"C", "E",	"C", "E",	"C", "E"
		};
		
		String[][] rslts = {{"Shopping", "Trek,Road Trip", "Museums,Sightseeing,Resort"} ,
				{"Skiing", "Mountaineering,Trek,Snow sledding","Mountaineering"},
				{"Spa","Music Concerts,Sightseeing,Museums","Resorts,Museums,Shopping"},
				{"Snow sledding", "Skiing", "Mountaineering"},
				{"Beach", "Yoga", "Spiritual"},
				{"Surfing", "Backpacking", "Biking"},
				{"Beach", "Concerts,Water Park", "Resort"},
				{"Safari", "Amusement park", "Off-road tours"}
		};

		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = null;
		System.out.println("Waiting for Connection");
		
		try {
			welcomeSocket = new ServerSocket(9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true)
		{
			Socket connectionSocket;
			try {
				connectionSocket = welcomeSocket.accept();
				System.out.println("Connected");
				BufferedReader inFromClient =
				new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				
				clientSentence = inFromClient.readLine();

				String[] params = clientSentence.split(","); 
				System.out.println("Received: " + clientSentence);
				
				VectImage Vec = new VectImage(0, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]));
				
				String res = alg2(Vec, data, rslts);
				
				String cities_final = fetch(cities2, res);
				System.out.println("Sending: " + cities_final);
				
				capitalizedSentence = cities_final + '\n';
				outToClient.writeBytes(capitalizedSentence);
				
				
				System.out.println("Sending: " + res);
				
				capitalizedSentence = res + '\n';
				outToClient.writeBytes(capitalizedSentence);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
					
	}
	
	public static String fetch(String[] cities, String label) {
		PriorityQueue<Locations> sum  = new PriorityQueue<Locations>(new Comparator<Locations>() {
			@Override
			public int compare(Locations param1, Locations param2) {
				return param2.score-param1.score;
			}
		});
		
		Random rand = new Random();

		String[] labels = label.split(",");
		
		WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("c5aa2e38-1f25-4222-b35e-afae8d4ac359");
		
        Map<String, String> queries = new HashMap<String, String>();
        
        System.out.println("X");
        //for(int i=0;i<1;i++) {
			for(int j=0;j<5;j++) {
				int random = rand.nextInt(100);

		        queries.put("q", cities[random]+" "+labels[0]+" (site_type:news) ");

		        queries.put("size", "1");
		        JsonElement result=null;
				try {
					result = webhoseClient.query("filterWebData", queries);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int rslt =  Integer.parseInt(result.getAsJsonObject().get("totalResults").toString());
				//System.out.println(rslt + " "+cities[random]);     // Print posts count
				queries.clear();
				sum.add(new Locations(cities[random],rslt));
				//System.out.println(sum.peek());
			}
		//}
        String rtrn= "";
        //Collect the Top 3 Locations
        for(int i=0;i<3;i++)
        	rtrn+=sum.poll().Name+",";
        
        return rtrn.substring(0, rtrn.length()-1);
	}
	
	public static String alg2(VectImage Vec, String[] data, String[][] rslts) {
		int counter=1, VecCounter=0;
		
		Integer[] arr = Vec.getVectArr();
		
		while(VecCounter<arr.length) {
			if(arr[VecCounter]<0) {
				counter*=2;
			}
			else
				counter = counter*2 +1;
			VecCounter++;
		}
		
		int max = 0, maxLocation=0;
		//Get Maximum Dominant without Price
		for(int i =0;i<arr.length-1;i++) {
			if(Math.abs(arr[i])>max) {
				max = Math.abs(arr[i]); 
				maxLocation=i;
			}
		}
		return rslts[(counter - 16)/2][maxLocation];
	}

}