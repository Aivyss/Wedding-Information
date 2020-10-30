package bucket;

import java.util.HashMap;
import java.util.Map;


public class Bucket {
	public Map<String, Integer> box;
	public String bucketBarCode;
	// public final int STORAGE = 10; 아주 나중에 구현
	// public int storageCount; 아주 나중에 구현
	public boolean lock;
	
	/**
	 * default 생성자
	 */
	public Bucket() {
		
	}
	/**
	 * 생성자 오버로딩
	 * @param bucketBarCode
	 */
	public Bucket(String bucketBarCode) {
		this.box = new HashMap<>();
		this.bucketBarCode = bucketBarCode; 
		//this.storageCount = 0; 아주 나중에 구현
		this.lock = false;
	}
	
	public boolean add(String productBarCode, int productCount) {
		if (!lock) {
			box.put(productBarCode, productCount);
			lock = true;
		} else {
			System.out.println("채울 수 없는 토트입니다.");
		}
		
		return lock;
	}
	
	public Integer getCount(String productBarCode) {
		return box.get(productBarCode);
	}
	public String getBucketBarCode() {
		return bucketBarCode;
	}
}
