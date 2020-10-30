package logisticsWarehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bucket.Bucket;

public class Warehouse {
	public String localCode;
	public final int FLOOR = 3;
	public final int POSITION = 150;
	public Map<String, OnePosition> positions; // <placeCode, OnePosition>

	/**
	 * 기본 생성자
	 */
	public Warehouse() {

	}

	/**
	 * 생성자 오버로딩
	 * 
	 * @param localCode
	 */
	public Warehouse(String localCode) {
		this.localCode = localCode;
		this.positions = new HashMap<>();
		String placeBarCode = "";

		for (int i = 1; i <= FLOOR; i++) {
			for (int j = 1; j <= POSITION; j++) {
				if (j >= 100) {
					placeBarCode = localCode + i + j;
				} else if (j >= 10) {
					placeBarCode = localCode + i + "0" + j;
				} else {
					placeBarCode = localCode + i + "00" + j;
				}

				positions.put(placeBarCode, new OnePosition(placeBarCode));
			}
		}
	}
	/**
	 * 입고처리를 하는 메소드
	 * @param placeCode
	 * @param count
	 * @return
	 */
	public boolean inBound(String placeCode, String productCode, int count) {
		OnePosition selectPosition = positions.get(placeCode);
		int storageCount = selectPosition.getStorageCount();
		boolean flag = true;
		
		if (count > storageCount || storageCount <= 0) {
			flag = false;
		} else {
			selectPosition.setStorageCount(storageCount - count);
			selectPosition.place.put(productCode, count);
		}
		
		return flag;
	}
	/**
	 * 출고처리를 하는 메소드
	 */
	public Map<String, Integer> outBound(List<String> productCodes, List<Integer> counts) {
		Map<String, Integer> outBounded = new HashMap<>();

		int index = 0;
		for (String code : productCodes) {
			int sum = 0;
			
			for (String placeCodeTmp : positions.keySet()) {
				int totalCount = positions.get(placeCodeTmp).place.get(code);
				
				if (totalCount >= counts.get(index)) {
					positions.get(placeCodeTmp).place.put(code, totalCount-counts.get(index));
					sum += counts.get(index);
					counts.set(index, 0);
				} else {
					sum += totalCount;
					positions.get(placeCodeTmp).place.put(code, 0);
					counts.set(index, counts.get(index)-totalCount);
				}
				
				if (counts.get(index) == 0) {
					break;
				}
			}
			
			outBounded.put(code, sum);
			index++;
		}
		
		return outBounded;
	}
}
