package vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductManage {
	/**
	 * 입고 처리 수량 등을 사용자로부터 입력받는 스캐너 인스턴스
	 */
	Scanner sc;

	/**
	 * 물품의 입고대기 수량을 나타내는 맵이다. key값은 바코드(String타입)으로 하였다.
	 */
	public Map<String, Integer> pMap;
	/**
	 * 입고 대기중인 물품들의 정보(VO class)를 저장하고 있는 리스트.
	 */
	public List<Product> pList;

	/**
	 * productManage 생성자
	 */
	public ProductManage() {
		pMap = new HashMap<>();
		pList = new ArrayList<>();
		sc = new Scanner(System.in);
	}

	/**
	 * 입고 대기할 상품을 추가하는 메소드
	 * 
	 * @param vo
	 * @param count
	 * @return
	 */
	public boolean addProduct(Product vo, int count) {
		String productCode = vo.getBarCode();
		boolean flag = true;

		if (pMap.get(productCode) == null) {
			pMap.put(productCode, count);
			pList.add(vo);

		} else {
			pMap.put(productCode, pMap.get(productCode) + count);
			flag = false;
		}

		return flag;
	}

	/**
	 * 바코드로 물품을 검색하여 정보를 가지고 있는 vo 객체를 꺼낸다. 일치하는 제품이 없다면 null을 반환한다.
	 * 
	 * @param productCode
	 * @return Product instance
	 */
	public Product searchProduct(String productCode) {
		Product output = null;

		if (pMap.get(productCode) != null) {
			for (Product vo : pList) {
				if (productCode.equals(vo.getBarCode())) {
					output = vo;
					break;
				}
			}
		}

		return output;
	}

	/**
	 * 입고처리 메소드 (반출 메소드)
	 */
	public ArrayList<Object> inBound(String productCode) throws InputMismatchException {
		Product vo = searchProduct(productCode);
		int totalCount = pMap.get(productCode);
		boolean flag = true;
		ArrayList<Object> list = new ArrayList<>();
		System.out.println("입고 대기중인 수량: " + totalCount + "개");
		
		try {
			while(true) {
				System.out.print("몇개를 입고처리 하겠습니까? : ");
				int count = sc.nextInt();
				if (totalCount < count) {
					System.out.println("최대 반출량보다 많습니다.");
					flag = false;
				} else {
					System.out.println("반출 처리 되었습니다.");
					if (totalCount-count == 0) {
						pMap.remove(productCode);
					} else {
						pMap.put(productCode, totalCount-count);						
					}
					
					flag = true;
					break;
				}
				
				list.add(flag);
				list.add(vo);
				list.add(count);
			}
		} catch (InputMismatchException e) {
			throw e;
		}
		
		return list;
	}
}
