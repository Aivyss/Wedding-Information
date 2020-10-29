package vo;

import java.util.List;

public class Product {
	// 멤버변수
	private String name;
	private String barCode; // key value
	private int weightFactor;
	private int volumeFactor;
	private int price;
	public static int count;
	
	/**
	 * 기본 생성자
	 */
	public Product() {
		
	}
	/**
	 * 특수 생성자
	 * @param productInfo
	 */
	public Product(List<Object> productInfo) {
		this.barCode= (String) productInfo.get(0);
		this.name = (String) productInfo.get(1);
		this.price = (Integer) productInfo.get(2);
		this.count += (Integer) productInfo.get(3);
	}
	
	// Getters
	public String getName() {
		return name;
	}
	public String getBarCode() {
		return barCode;
	}
	public int getWeightFactor() {
		return weightFactor;
	}
	public int getVolumeFactor() {
		return volumeFactor;
	}
	public int getPrice() {
		return price;
	}
}
