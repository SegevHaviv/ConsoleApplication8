package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import imageSelector.ImageSelector;

public class Server {

	private BufferedReader br;
	private PrintWriter pw;
	private ServerSocket server;
	private volatile boolean exit = false;

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public void start() throws Exception {

		try {
			server = new ServerSocket(2200);

			server.setSoTimeout(0);
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!exit) {
			Socket aClient;

			try {
				aClient = server.accept();

				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {

						try {
							handleClient(aClient);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});

				t1.start();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}


	}

	public void stop() {

		exit = true;
		try {
			br.close();
			pw.close();

			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void handleClient(Socket aClient) throws IOException {

		InputStream istream = aClient.getInputStream();
		OutputStream ostream = aClient.getOutputStream();
		br = new BufferedReader(new InputStreamReader(istream));
		pw = new PrintWriter(ostream);
		String temp = br.readLine();
		int numOfImages = Integer.parseInt(temp);

		ImageSelector is = new ImageSelector(null);

		int viReturend[];
		if (numOfImages == 0) {

			viReturend = is.generateImages(9);

			for (int imageNum : viReturend) {

				pw.println(imageNum);
				pw.flush();

			}
		} else {
			int[] imageArr = new int[numOfImages];
			
			
			for (int i = 0; i < numOfImages; i++) {
				try {
					temp = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageArr[i] = Integer.parseInt(temp);
			}
			
			 is = new ImageSelector(imageArr);

			temp = br.readLine();
			numOfImages = Integer.parseInt(temp);
			int[] selected = new int[numOfImages];

			for (int i = 0; i < numOfImages; i++) {
				try {
					temp = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selected[i] = Integer.parseInt(temp);
			}
			
			
			is.generateChosen(selected);
		
				
			
			
				
			
			viReturend = is.generateImages(9);

			for (int imageNum : viReturend) {

				pw.println(imageNum);
				pw.flush();
			}
			
		

			
		}
		
		pw.println(is.getChosen()[2].intValue());
		pw.println(is.getChosen()[1].intValue());
		pw.println(is.getChosen()[3].intValue());
		pw.println(is.getChosen()[0].intValue());
		pw.flush();
	}

}
