package vo;

import java.util.ArrayList;

public class Human {
	// ��������
	String id;
	String password;
	String level;
	// �Ż�����
	String name;
	boolean sex;
	int age;
	double bmi;
	// �ɷ�����
	String latestEdu;
	int salary;
	// ����
	int totalScore;
	double nomalizedTotalScore;
	int LatestEduScore;
	int SalaryScore;
	int bodyShapeScore;
	//�����ݾ�
	int cash;
	//��Ī �����
	boolean invited;
	String matchedId;
	
	/**
	 * �⺻ ������
	 */
	public Human() {
		
	}
	
	/**
	 * ������ �����ε� 1
	 */
	public Human(String name, String id, boolean sex, String latestEdu, String level, String password, int age,
			int salary, double bmi) {
		this.name = name;
		this.id = id;
		this.sex = sex;
		this.latestEdu = latestEdu;
		this.level = level;
		this.password = password;
		this.age = age;
		this.salary = salary;
		this.bmi = bmi;
	}
	
	/**
	 * ������ �����ε� 2
	 * @param humanInfo
	 */
	public Human(ArrayList<Object> humanInfo) {
		this.id = (String) humanInfo.get(0);
		this.password = (String) humanInfo.get(1);
		
		this.name = (String) humanInfo.get(2);
		this.sex = (Boolean) humanInfo.get(3);
		this.age = (Integer) humanInfo.get(4);
		this.bmi = (Double) humanInfo.get(5);
		
		this.latestEdu = (String) humanInfo.get(6);
		this.salary = (Integer) humanInfo.get(7);
		
		this.nomalizedTotalScore = 0;
	}
	
	// setter�� getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLatestEdu() {
		return latestEdu;
	}

	public void setLatestEdu(String latestEdu) {
		this.latestEdu = latestEdu;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public double getNomalizedTotalScore() {
		return nomalizedTotalScore;
	}

	public void setNomalizedTotalScore(double nomalizedTotalScore) {
		this.nomalizedTotalScore = nomalizedTotalScore;
	}

	public int getLatestEduScore() {
		return LatestEduScore;
	}

	public void setLatestEduScore(int latestEduScore) {
		LatestEduScore = latestEduScore;
	}

	public int getSalaryScore() {
		return SalaryScore;
	}

	public void setSalaryScore(int salaryScore) {
		SalaryScore = salaryScore;
	}

	public int getBodyShapeScore() {
		return bodyShapeScore;
	}

	public void setBodyShapeScore(int bodyShapeScore) {
		this.bodyShapeScore = bodyShapeScore;
	}

	public boolean isInvited() {
		return invited;
	}

	public void setInvited(boolean invited) {
		this.invited = invited;
	}

	public String getMatchedId() {
		return matchedId;
	}

	public void setMatchedId(String matchedId) {
		this.matchedId = matchedId;
	}
}
