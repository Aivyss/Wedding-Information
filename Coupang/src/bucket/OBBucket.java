package bucket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OBBucket extends Bucket {
	String customerCode;
	Map<String, Integer> orderList;
	
	public OBBucket(String bucketBarCode) {
		super(bucketBarCode);
		orderList = new HashMap<>();
	}
	
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public void setOrderList(String productCode, int count) {
		this.orderList.put(productCode, count);
	}
}
