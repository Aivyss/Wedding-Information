package logisticsWarehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bucket.Bucket;
import vo.Product;

public class OnePosition {
	/**
	 * placeBarCode는 사실상 좌표를 의미한다. STORAGE는 최대 용적량이다.
	 */
	private String placeBarCode; // 그 자리의 좌표를 의미한다.
	private final int STORAGE = 15;
	public Map<String, Integer> place; // <productCode, count>
	private int storageCount; // 적재 가능한 물품의 갯수.

	/**
	 * 기본 생성자
	 */
	public OnePosition() {

	}

	/**
	 * 특수 생성자
	 * 
	 * @param placeBarCode
	 */
	public OnePosition(String placeBarCode) {
		this.placeBarCode = placeBarCode;
		this.place = new HashMap<>();
		this.storageCount = STORAGE;
	}

	/**
	 * 용적 카운트를 반환하는 메소드
	 */
	public int getStorageCount() {
		return storageCount;
	}
	
	public void setStorageCount(int storageCount) {
		this.storageCount = storageCount;
	}

	public String getPlaceBarCode() {
		return placeBarCode;
	}
	
	public int getSTORAGE() {
		return STORAGE;
	}
	
}
