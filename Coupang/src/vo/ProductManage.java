package vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductManage {
	/**
	 * 물품의 입고대기 수량을 나타내는 맵이다. key값은 바코드(String타입)으로 하였다.
	 */
	public Map<String, Product> pMap;
	/**
	 * 입고 대기중인 물품들의 정보(VO class)를 저장하고 있는 리스트.
	 */

	/**
	 * productManage 생성자
	 */
	public ProductManage() {
		pMap = new HashMap<>();
	}

	/**
	 * 물품의 정보를 입력하는 메소드
	 * 
	 * @param vo
	 * @param count
	 * @return
	 */
	public boolean addProductInfo(Product vo) {
		boolean flag = false;
		
		if (pMap.get(vo.getBarCode())==null) {
			pMap.put(vo.getBarCode(), vo);
			flag = true; // 정보를 추가하였음.
		} 
		
		return flag;
	}
	
	/**
	 * 해당되는 정보의 제품의 입고대기 수량을 추가하는 메소드.
	 */
	public boolean addProductCount(String productCode, int count) {
		Product vo = pMap.get(productCode);
		boolean flag = true;
		if (pMap.get(productCode) != null) {
			vo.setCount(vo.getCount()+count);
		} else {
			flag = false;
		}
		return false;
	}
	
	/**
	 * 물품정보 탐색 메소드
	 */
	public Product searchProduct(String productCode) {
		Product result = pMap.get(productCode);
		
		return result;
	}

	/**
	 * 반출 반영 메소드
	 */
	public void updateCount(String productCode, int count) {
		Product vo = pMap.get(productCode);
	}
}
