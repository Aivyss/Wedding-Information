package vo;

import java.util.HashMap;

public class Female extends Human {
	int ageScore;
	int surgery;
	
	/**
	 * 기본생성자
	 */
	public Female() {
		
	}
	
	/**
	 * 여성 객체를 생성하는 생성자.
	 * @param humanInfo
	 */
	public Female(HashMap<String, Object> info) {
		super(info);
		surgery = (Integer) info.get("surgery");
	}
	
	/**
	 * vo객체의 정보를 출력하는 메소드
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(super.toString() +", ");
		buf.append("성형여부: ");
		if (this.surgery == 4) {
			buf.append("자연산");
		} else if (this.surgery == 3) {
			buf.append("반자연산");
		} else if (this.surgery == 2) {
			buf.append("반양식");
		} else if (this.surgery == 1) {
			 buf.append("양식");
		}

		return buf.toString();
	}
	
	// Getter and Setter
	public void setAgeScore(int ageScore) {
		this.ageScore = ageScore;
	}

	public int getsurgery() {
		return surgery;
	}
}