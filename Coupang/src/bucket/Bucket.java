package bucket;

import java.util.ArrayList;

import vo.Product;

public class Bucket<K> {
	ArrayList<K> productBarCode;
	ArrayList<Integer> productCount;
	public String bucketBarCode;
	public final int STORAGE = 10;
	public int storageCount;
	public boolean lock;
	
	public Bucket(String bucketBarCode) {
		this.productBarCode = new ArrayList<>();
		this.productCount = new ArrayList<>();
		this.bucketBarCode = bucketBarCode;
		this.storageCount = 0;
		this.lock = false;
	}
	
	public boolean add(K productBarCode, int productCount) {
		boolean flag = false;
		
		for (int i=0; i<this.productBarCode.size(); i++) {
			if (productBarCode.equals(this.productBarCode.get(i))) {
				this.productCount.set(i, this.productCount.get(i) + productCount);
				flag = true;
				break;
			}
		}
		
		return false;
	}
	
	public Integer getCount(K productBarCode) {
		int index = 0;
		
		for (int i=0; i<this.productBarCode.size(); i++) {
			if (productBarCode.equals(this.productBarCode.get(i))) {
				index = i;
				break;
			}
		}
		
		int result = this.productCount.get(index);
		
		return result;
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
