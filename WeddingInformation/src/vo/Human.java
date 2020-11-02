package vo;

import java.util.HashMap;

public class Human {
	// 계정정보
	String id;
	String password;
	String grade;
	int level;
	// 신상정보
	String name;
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
	// 어카운트 락 (계정을 3회 이상 실패시 로그인을 불가능하게 함)
	boolean accountLock;
	int lockCount;
	
	/**
	 * 기본 생성자
	 */
	public Human() {
		
	}
	
	/**
	 * 생성자 오버로딩
	 * @param humanInfo
	 */
	public Human(HashMap<String, Object> info) {
		this.id = (String) info.get("id");
		this.password = (String) info.get("password");
		
		this.name = (String) info.get("name");
		this.age = (Integer) info.get("age");
		this.height = (Integer) info.get("height");
		this.bmi = (Double) info.get("bmi");
		
		this.latestEdu = (String) info.get("latestEdu");
		this.latestEduLevel = (Integer) info.get("latestEduLevel");
		this.salary = (Integer) info.get("salary");
		
		this.nomalizedTotalScore = 0;
		
		this.matchedId = null;
		this.lock = false;
		this.success = false;
		this.accountLock = false;
		this.lockCount = 0;
	}
	
	/**
	 * vo객체의 정보를 출력하는 메소드
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

	public String getId() {
		return id;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void setCash(int cash) {
		this.cash = cash;
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

	public void setHeightScore(int heightScore) {
		this.heightScore = heightScore;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setAccountLock(boolean accountLock) {
		this.accountLock = accountLock;
	}

	public boolean isAccountLock() {
		return accountLock;
	}

	public int getLockCount() {
		return lockCount;
	}

	public void setLockCount(int lockCount) {
		this.lockCount = lockCount;
	}	
}