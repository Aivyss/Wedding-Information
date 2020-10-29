package bucket;

import java.util.HashMap;
import java.util.Map;


public class Bucket<K> {
	public Map<K, Integer> box;
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
	
	public boolean add(K productBarCode, int productCount) {
		if (!lock) {
			box.put(productBarCode, productCount);
			lock = true;
		} else {
			System.out.println("채울 수 없는 토트입니다.");
		}
		
		return lock;
	}
	
	public Integer getCount(K productBarCode) {
		return box.get(productBarCode);
	}
	public String getBucketBarCode() {
		return bucketBarCode;
	}
	
	
	
//	public String bucketBarCode;
//	public final int STORAGE = 10;
//	public int storageCount;
//	public boolean lock;
//	private Map<String, Integer> bucket;
//	
//	/**
//	 * Constructor overloading
//	 * @param bucketBarCode
//	 */
//	public Bucket(String bucketBarCode) {
//		this.bucketBarCode = bucketBarCode;
//		this.storageCount = 0;
//		this.bucket = new HashMap<>();
//		this.lock = false;
//	}
//	
//	/**
//	 * add Product
//	 * @param vo
//	 * @return boolean
//	 */
//	public boolean add(String productBarCode, int count) {
//		boolean flag = true;
//		if (storageCount > STORAGE) {
//			System.out.println();
//			lock = true;
//			flag = false;
//		} else {
//			count += bucket.get(productBarCode);
//			bucket.replace(productBarCode, count);
//		}
//		
//		return flag;
//	}
//	
//	/**
//	 * get bucket method
//	 * @return list<Product>
//	 */
//	public Map<String, Integer> getBucket() {
//		return bucket;
//	}
}
