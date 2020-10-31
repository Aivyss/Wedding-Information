package vo;

import java.util.ArrayList;

public class Male extends Human {
	boolean taco = false;
	
	public Male(ArrayList<Object> humanInfo) {
		super(humanInfo);
		this.taco = taco;
	}

	public boolean isTaco() {
		return taco;
	}

	public void setTaco(boolean taco) {
		this.taco = taco;
	}
	
	
}
