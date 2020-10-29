package vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductManage {
	public Map<String, Product> pManage;
	
	/**
	 * productManage 생성자
	 */
	public ProductManage() {
		pManage = new HashMap<>();
	}
	
	public boolean addProduct(Product vo) {
		String productBarCode = vo.getBarCode();
		boolean flag = true;
		
		if (pManage.get(productBarCode) == null) {
			pManage.put(productBarCode, vo);
		} else {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean searchProduct(String productBarCode) {
		boolean flag = false;
		
		if(pManage.get(productBarCode) != null) {
			flag =true;
		}
		
		return flag;
	}
	
	@Override
	public String toString() {
		Set<String> keySet = pManage.keySet();
		String output = "";
		for (String string : keySet) {
			output = "바코드: " + string + ", 물품 갯수: ";
		}
		return null;
	}
}
