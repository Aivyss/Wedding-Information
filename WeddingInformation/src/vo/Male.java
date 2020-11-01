package vo;

import java.util.HashMap;

public class Male extends Human {
	boolean taco;
	
	/**
	 * 기본 생성자
	 */
	public Male() {
		
	}
	/**
	 * 생성자 오버로딩
	 * @param info
	 */
	public Male(HashMap<String, Object> info) {
		super(info);
		this.taco = (Boolean) info.get("taco");
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
