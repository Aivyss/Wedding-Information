package vo;

import java.util.ArrayList;

public class Male extends Human {
	boolean taco;
	
	public Male(ArrayList<Object> humanInfo) {
		super(humanInfo);
		this.taco = (Boolean) humanInfo.get(8);
	}

	public boolean isTaco() {
		return taco;
	}

	public void setTaco(boolean taco) {
		this.taco = taco;
	}
	
	
}
