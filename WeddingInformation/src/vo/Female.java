package vo;

import java.util.ArrayList;

public class Female extends Human {
	int ageScore;
	int surgeryScore;
	
	/**
	 * 여성 객체를 생성하는 생성자.
	 * @param humanInfo
	 */
	public Female(ArrayList<Object> humanInfo) {
		super(humanInfo);
		surgeryScore = (Integer) humanInfo.get(10);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(super.toString() +", ");
		buf.append("성형여부: ");
		if (this.surgeryScore == 4) {
			buf.append("자연산");
		} else if (this.surgeryScore == 3) {
			buf.append("반자연산");
		} else if (this.surgeryScore == 2) {
			buf.append("반양식");
		} else if (this.surgeryScore == 1) {
			 buf.append("양식");
		}

		return buf.toString();
	}

	public int getAgeScore() {
		return ageScore;
	}

	public void setAgeScore(int ageScore) {
		this.ageScore = ageScore;
	}

	public int getSurgeryScore() {
		return surgeryScore;
	}

	public void setSurgeryScore(int surgeryScore) {
		this.surgeryScore = surgeryScore;
	}
	
	
}
