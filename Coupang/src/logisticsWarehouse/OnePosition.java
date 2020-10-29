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
	private Map<String, Integer> place;
	private int storageCount; // 남은 용적 카운트를 의미.

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
		this.storageCount = 0;
	}

	/**
	 * 해당 자리에 물품을 입고하는 기능을 수행하는 메소드. 해당 제품을 적재시 true. 적재 실패시 false를 반환.
	 * 
	 * @param vo
	 * @return
	 */
	public Bucket<String> addProduct(Bucket<String> bucket) {
		Set<String> tmp1 = bucket.box.keySet();
		String productCode = new ArrayList<>(tmp1).get(0);

		if (storageCount == STORAGE) {
			System.out.println("이 자리는 꽉 차 있습니다.");
		} else {
			int productCount = bucket.box.get(productCode); // 토트에 있는 제품의 수량
			
			if (productCount >= (STORAGE - storageCount)) {
				productCount -= (STORAGE - storageCount);
				
				if (productCount == 0) {
					bucket.box.remove(productCode);
					bucket.lock = false;
				} else {
					bucket.box.put(productCode, productCount);
				}
				
				place.put(productCode, STORAGE - storageCount);

				System.out.println("물품을 성공적으로 적재했습니다.");
			}
		}

		return bucket;
	}

	/**
	 * 해당 자리에 바코드와 일치하는 물품을 출고하는 기능을 수행하는 메소드. 해당 제품이 있어서 출고가 가능할 시 true, 자리가 비어있거나
	 * 해당 제품이 없을시 false
	 * 
	 * @param barCode
	 * @return
	 */
	public boolean getProduct(String barCode) {
		boolean flag = true;

		return flag;
	}

	/**
	 * 용적 카운트를 반환하는 메소드
	 */
	public int getStorageCount() {
		return storageCount;
	}

	public String getPlaceBarCode() {
		return placeBarCode;
	}

}
