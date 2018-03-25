package model;

import java.io.Serializable;

import java.io.Serializable;

public class VectImage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int imageNum;
	private Integer vectArr[];
	

	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public VectImage() {
		// TODO Auto-generated constructor stub
	}
	public VectImage(int imageNum, int x ,int y, int z, int k) {
		this.imageNum = imageNum;
		this.vectArr= new Integer[4];
		vectArr[0]=x;
		vectArr[1]=y;
		vectArr[2]=z;
		vectArr[3]=k;
		
		
		
	}
	
	public int getImageNum() {
		return imageNum;
	}

	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	public Integer[] getVectArr() {
		return vectArr;
	}

	public void setVectArr(Integer[] vectArr) {
		this.vectArr = vectArr;
	}

	public int sumVects(){
		int sum=0;
		
		for(int i=0;i<vectArr.length;i++){
			sum+=vectArr[i];
		}
		
		return sum;
	}
}