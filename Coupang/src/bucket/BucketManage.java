package bucket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BucketManage {
	public Map<String, Bucket> bMap; // <bucketCode, bucket>
	
	public BucketManage() {
		this.bMap = new HashMap<>();
		
		for (int i=0; i<100; i++) {
			Bucket tmp = null;
			if (i>=100) {
				tmp = new Bucket(Integer.toString(i));
			} else if (i>=10) {
				tmp = new Bucket("0" + Integer.toString(i));
			} else {
				tmp = new Bucket("00" + Integer.toString(i));
			}
			
			bMap.put(tmp.bucketBarCode, tmp);
		}
	}
	
	public void add(Bucket bucket) {
		this.bMap.put(bucket.getBucketBarCode(), bucket);
	}

	public Map<String, Bucket> getbList() {
		return bMap;
	}
}
