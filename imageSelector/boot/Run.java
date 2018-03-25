package boot;

import server.Server;

public class Run {

	public static void main(String[] args) {

	/*	try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("image.txt")));
			StringBuilder sb = new StringBuilder();
			String[] vec;
			ArrayList<VectImage> va = new ArrayList<VectImage>();
			String line = null;
			try {
				while ((line = br.readLine()) != null) {

					
					System.out.println(line);
					vec = line.split("	");
					VectImage vi = new VectImage(Integer.parseInt(vec[0]), Integer.parseInt(vec[1]),
							Integer.parseInt(vec[2]), Integer.parseInt(vec[3]), Integer.parseInt(vec[4]));
					va.add(vi);
				}

				XMLEncoder xe = new XMLEncoder(new FileOutputStream("images.xml"));

				xe.writeObject(va);

				xe.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/

		
	Server server = new Server();try
	{
		server.start();
	}catch(
	Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
