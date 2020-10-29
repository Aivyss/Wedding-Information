import java.util.ArrayList;

public class Buck1<K, V> {
	ArrayList<K> productBarCode;
	ArrayList<V> productCount;
	
	public Buck1() {
		this.productBarCode = new ArrayList<>();
		this.productCount = new ArrayList<>();
	}
	
	public V getCount(K productBarCode) {
		int index = 0;
		
		for (int i=0; i<this.productBarCode.size(); i++) {
			if (productBarCode.equals(this.productBarCode.get(i))) {
				index = i;
				break;
			}
		}
		
		V result = this.productCount.get(index);
		
		return result;
	}
}
