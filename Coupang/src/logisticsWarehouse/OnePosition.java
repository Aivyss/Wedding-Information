package logisticsWarehouse;

import java.util.HashMap;
import java.util.Map;

import vo.Product;

public class OnePosition {
	/**
	 * placeBarCode는 사실상 좌표를 의미한다.
	 * STORAGE는 최대 용적량이다.
	 */
	private String placeBarCode; // 그 자리의 좌표를 의미한다.
	private final int STORAGE = 15;
	private Map<String, Product> box;
	private int storageCount; // 남은 용적 카운트를 의미.
	
	
	/**
	 * 기본 생성자
	 */
	public OnePosition() {
		
	}
	/**
	 * 특수 생성자
	 * @param placeBarCode
	 */
	public OnePosition(String placeBarCode) {
		this.placeBarCode = placeBarCode;
		this.box = new HashMap<>();
		this.storageCount = this.STORAGE;
	}
	
	/**
	 * 해당 자리에 물품을 입고하는 기능을 수행하는 메소드.
	 * 해당 제품을 적재시 true. 적재 실패시 false를 반환.
	 * @param vo
	 * @return
	 */
	public boolean addProduct(Product vo) {
		boolean flag = true;
		
		if (storageCount > 0) {
			this.box.put(vo.getBarCode(), vo);
			storageCount--;
		} else {
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 해당 자리에 바코드와 일치하는 물품을 출고하는 기능을 수행하는 메소드.
	 * 해당 제품이 있어서 출고가 가능할 시 true, 자리가 비어있거나 해당 제품이 없을시 false
	 * @param barCode
	 * @return
	 */
	public boolean getProduct(String barCode) {
		boolean flag = true;
		
		if (storageCount <= 0) {
			flag = false; // 자리가 빈 자리인 경우.
		} else {
			Product vo = box.get(barCode);
			
			if (vo == null) { // 자리가 빈자리는 아니지만 해당 상품이 없는 경우.
				flag = false;
			}
		}
		
		return flag;
	}
	
	/**
	 * 용적 카운트를 반환하는 메소드
	 */
	public int getStorageCount() {
		return storageCount;
	}
	
}
