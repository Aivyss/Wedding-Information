package vo;

import java.util.ArrayList;

public class Human {
	// 계정정보
	String id;
	String password;
	String grade;
	int level;
	// 신상정보
	String name;
	boolean sex;
	int age;
	int height;
	double bmi;
	// 능력정보
	String latestEdu;
	int latestEduLevel;
	int salary;
	// 점수
	int totalScore;
	double nomalizedTotalScore;
	int LatestEduScore;
	int SalaryScore;
	int heightScore;
	//충전금액
	int cash;
	//매칭 억셉터
	boolean invited;
	String matchedId;
	//매칭 락
	boolean lock;
	boolean success;
	
	/**
	 * 기본 생성자
	 */
	public Human() {
		
	}
	
	/**
	 * 생성자 오버로딩 2
	 * @param humanInfo
	 */
	public Human(ArrayList<Object> humanInfo) {
		this.id = (String) humanInfo.get(0);
		this.password = (String) humanInfo.get(1);
		
		this.name = (String) humanInfo.get(2);
		this.sex = (Boolean) humanInfo.get(3);
		this.age = (Integer) humanInfo.get(4);
		this.height = (Integer) humanInfo.get(5);
		this.bmi = (Double) humanInfo.get(6);
		
		this.latestEdu = (String) humanInfo.get(7);
		this.latestEduLevel = (Integer) humanInfo.get(8);
		this.salary = (Integer) humanInfo.get(9);
		
		this.nomalizedTotalScore = 0;
		
		this.matchedId = null;
		this.lock = false;
		this.success = false;
	}
	
	/**
	 * vo의 정보를 출력하는 메소드
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("등급: ");
		buf.append(grade + ", ");
		buf.append("이름: ");
		buf.append(this.name + ", ");
		buf.append("나이: ");
		buf.append(this.age);
		buf.append("성별: ");
		if(this.sex) {
			buf.append("남성, ");
		} else {
			buf.append("여성, ");
		}
		buf.append("학벌");
		buf.append(latestEdu+ ", ");
		buf.append("연봉");
		buf.append(Integer.toString(salary) + ", ");
		buf.append("BMI");
		buf.append(Double.toString(bmi));
		buf.append("충전금: ");
		buf.append(Integer.toString(cash)+"원");
		
		return buf.toString();
	}
	// setter와 getter
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public String getGrade() {
		return grade;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeightScore() {
		return heightScore;
	}

	public void setHeightScore(int heightScore) {
		this.heightScore = heightScore;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}	
	
}
