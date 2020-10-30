package vo;

import java.util.ArrayList;

public class Female extends Human {
	int ageScore;
	int surgeryScore;
	
	/**
	 * ���� ��ü�� �����ϴ� ������.
	 * @param humanInfo
	 */
	public Female(ArrayList<Object> humanInfo) {
		super(humanInfo);
		surgeryScore = (Integer) humanInfo.get(8);
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
