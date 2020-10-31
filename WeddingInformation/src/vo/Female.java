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
		surgeryScore = (Integer) humanInfo.get(10);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(super.toString() +", ");
		buf.append("��������: ");
		if (this.surgeryScore == 4) {
			buf.append("�ڿ���");
		} else if (this.surgeryScore == 3) {
			buf.append("���ڿ���");
		} else if (this.surgeryScore == 2) {
			buf.append("�ݾ��");
		} else if (this.surgeryScore == 1) {
			 buf.append("���");
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
