package vo;

import java.util.ArrayList;

public class Female extends Human {
	int ageScore;
	int surgeryScore;
	
	public Female(ArrayList<Object> humanInfo) {
		super(humanInfo);
		surgeryScore = (Integer) humanInfo.get(8);
	}
}
