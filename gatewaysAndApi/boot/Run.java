package boot;

import java.io.IOException;
import java.text.ParseException;

import server.Server;

public class Run {
	public static void main(String[] args) throws IOException, ParseException {
		Server server = new Server();
		server.start();
	}
}
