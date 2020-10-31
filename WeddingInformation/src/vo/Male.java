package vo;

import java.util.ArrayList;

public class Male extends Human {
	boolean taco;
	
	public Male(ArrayList<Object> humanInfo) {
		super(humanInfo);
		this.taco = (Boolean) humanInfo.get(10);
	}
	
	/**
	 * vo객체의 정보를 출력하는 메소드
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(super.toString() +", ");
		buf.append("탈모여부: ");
		if(taco) {
			buf.append("O");
		} else {
			buf.append("풍성");
		}
		
		return buf.toString();
	}
	
	// Getter and Setter
	public boolean isTaco() {
		return taco;
	}
}
