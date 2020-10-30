package vo;

import java.util.List;

public class Product {
	// 멤버변수
	private String name;
	private String barCode; // key value
	private int weightFactor;
	private int volumeFactor;
	private int price;
	private int count;
	
	/**
	 * 기본 생성자
	 */
	public Product() {
		
	}
	/**
	 * 생성자 오버로딩
	 * @param productInfo
	 */
	public Product(List<Object> productInfo) {
		this.barCode= (String) productInfo.get(0);
		this.name = (String) productInfo.get(1);
		this.price = (Integer) productInfo.get(2);
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
