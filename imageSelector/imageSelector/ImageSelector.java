package imageSelector;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

import vectImage.VectImage;

public class ImageSelector {

	private PriorityQueue<VectImage> pq;
	private HashMap<Integer, VectImage> hm;
	private Integer[] chosen;
	private ArrayList<VectImage> viArray;

	public HashMap<Integer, VectImage> getHm() {
		return hm;
	}

	public void setHm(HashMap<Integer, VectImage> hm) {
		this.hm = hm;
	}

	public ArrayList<VectImage> getViArray() {
		return viArray;
	}

	public void setViArray(ArrayList<VectImage> viArray) {
		this.viArray = viArray;
	}

	public PriorityQueue<VectImage> getPq() {
		return pq;
	}

	public void setPq(PriorityQueue<VectImage> pq) {
		this.pq = pq;
	}

	public Integer[] getChosen() {
		return chosen;
	}

	public void setChosen(Integer[] chosen) {
		this.chosen = chosen;
	}

	public void generateChosen(int[] images) {

		chosen = new Integer[4];

		chosen[0] = 0;
		chosen[1] = 0;
		chosen[2] = 0;
		chosen[3] = 0;

		for (int i = 0; i < images.length; i++) {

			chosen[0] += hm.get(images[i]).getVectArr()[0];
			chosen[1] += hm.get(images[i]).getVectArr()[1];
			chosen[2] += hm.get(images[i]).getVectArr()[2];
			chosen[3] += hm.get(images[i]).getVectArr()[3];

		}
		chosen[0] = (chosen[0] / images.length);
		chosen[1] = (chosen[1] / images.length);
		chosen[2] = (chosen[2] / images.length);
		chosen[3] = (chosen[3] / images.length);
		System.out.println("0: " + chosen[0] + " 1: " + chosen[1] + " 2: " + chosen[2] + " 3: " + chosen[3]);
	}

	@SuppressWarnings("unchecked")
	public ImageSelector(int[] selected) {

		chosen = new Integer[4];

		Random rand = new Random();
		chosen[0] = rand.nextInt((10 + 10) + 1) - 10;
		chosen[1] = rand.nextInt((10 + 10) + 1) - 10;
		chosen[2] = rand.nextInt((10 + 10) + 1) - 10;
		chosen[3] = rand.nextInt((10 + 10) + 1) - 10;
		try {
			XMLDecoder xe = new XMLDecoder(new FileInputStream("images.xml"));
			viArray = (ArrayList<VectImage>) xe.readObject();

			xe.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hm = new HashMap<>();

		pq = new PriorityQueue<>(new Comparator<VectImage>() {

			@Override
			public int compare(VectImage vi1, VectImage vi2) {

				int dif1 = 0;
				int dif2 = 0;
				Integer[] va1 = vi1.getVectArr();
				for (int i = 0; i < va1.length; i++) {

					dif1 += Math.abs(va1[i] - chosen[i]);

				}

				Integer[] va2 = vi2.getVectArr();
				for (int i = 0; i < va2.length; i++) {

					dif2 += Math.abs(va2[i] - chosen[i]);

				}
				return dif2 - dif1;
			}

		});

		for (VectImage vi : viArray) {

			hm.put(vi.getImageNum(), vi);
			if (selected != null) {
				boolean flag = true;
				for (int i = 0; i < selected.length; i++) {
					if (selected[i] == vi.getImageNum()) {
						flag = false;
						break;
					}
				}
				if (flag)
					pq.add(vi);
			} else
				pq.add(vi);
		}

	}

	public int[] generateImages(int numOfimages) {

		if (chosen == null)
			return null;

		int imagenums[] = new int[numOfimages];
		VectImage vi = null;
		for (int i = 0; i < numOfimages; i++) {

			if ((vi = pq.poll()) != null)
				imagenums[i] = vi.getImageNum();

		}
		return imagenums;

	}

}
