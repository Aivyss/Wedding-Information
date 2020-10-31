package vo;

import java.util.ArrayList;

public class Male extends Human {
	boolean taco;
	
	public Male(ArrayList<Object> humanInfo) {
		super(humanInfo);
		this.taco = (Boolean) humanInfo.get(10);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(super.toString() +", ");
		buf.append("Å»¸ð¿©ºÎ: ");
		if(taco) {
			buf.append("O");
		} else {
			buf.append("Ç³¼º");
		}
		
		return buf.toString();
	}

	public boolean isTaco() {
		return taco;
	}

	public void setTaco(boolean taco) {
		this.taco = taco;
	}
	
	
}
