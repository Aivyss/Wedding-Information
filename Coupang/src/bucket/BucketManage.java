package bucket;

import java.util.ArrayList;
import java.util.List;

public class BucketManage {
	public List<Bucket<String>> bList;
	
	public BucketManage() {
		this.bList = new ArrayList<>();
		
		for (int i=0; i<100; i++) {
			Bucket<String> tmp = null;
			if (i>=100) {
				tmp = new Bucket<>(Integer.toString(i));
			} else if (i>=10) {
				tmp = new Bucket<>("0" + Integer.toString(i));
			} else {
				tmp = new Bucket<>("00" + Integer.toString(i));
			}
			
			bList.add(tmp);
		}
	}
	
	public void add(Bucket<String> bucket) {
		this.bList.add(bucket);
	}

	public List<Bucket<String>> getbList() {
		return bList;
	}
}
