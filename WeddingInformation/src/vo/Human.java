package vo;

import java.util.HashMap;

public class Human {
	// 계정정보
	private String id;
	private String password;
	private String grade;
	private int gradeIndex;
	// 신상정보
	private String name;
	private int age;
	private int height;
	private double bmi;
	private boolean sex;
	// 능력정보
	private String latestEdu;
	private int latestEduIndex;
	private int salary;
	// 점수
	private int totalScore;
	private double normalizedTotalScore;
	private int latestEduScore;
	private int salaryScore;
	private int heightScore;
	//충전금액
	private int cash;
	//매칭 억셉터
	private boolean invited;
	private String matchedId;
	//매칭 락
	private boolean lock;
	private boolean success;
	// 어카운트 락 (계정을 3회 이상 실패시 로그인을 불가능하게 함)
	private boolean accountLock;
	private int lockCount;
	
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
		this.sex = (Boolean) info.get("sex");
		
		this.latestEdu = (String) info.get("latestEdu");
		this.latestEduIndex = (Integer) info.get("latestEduIndex");
		this.salary = (Integer) info.get("salary");
		
		this.normalizedTotalScore = 0;
		
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
		buf.append(Integer.toString(cash)+"원, ");
		buf.append("총점수: ");
		buf.append(totalScore);
		buf.append(", 환산점수: ");
		buf.append(normalizedTotalScore);
		
		
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
	
	public double getNormalizedTotalScore() {
		return normalizedTotalScore;
	}
	
	public void setNormalizedTotalScore(double normalizedTotalScore) {
		this.normalizedTotalScore = normalizedTotalScore;
	}
	
	public int getLatestEduScore() {
		return latestEduScore;
	}
	
	public void setLatestEduScore(int latestEduScore) {
		this.latestEduScore = latestEduScore;
	}
	
	public void setSalaryScore(int salaryScore) {
		this.salaryScore = salaryScore;
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
	
	public int getGradeIndex() {
		return gradeIndex;
	}
	
	public void setGradeIndex(int gradeIndex) {
		this.gradeIndex = gradeIndex;
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


	public int getLockCount() {
		return lockCount;
	}

	public void setLockCount(int lockCount) {
		this.lockCount = lockCount;
	}

	public int getLatestEduIndex() {
		return latestEduIndex;
	}

	public double getBmi() {
		return bmi;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public int getSalaryScore() {
		return salaryScore;
	}

	public int getHeightScore() {
		return heightScore;
	}

	public String getLatestEdu() {
		return latestEdu;
	}

	public void setLatestEdu(String latestEdu) {
		this.latestEdu = latestEdu;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public void setLatestEduIndex(int latestEduIndex) {
		this.latestEduIndex = latestEduIndex;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}


	public void setSex(boolean sex) {
		this.sex = sex;
	}	
	
	public boolean isSex() {
		return sex;
	}
	
	public boolean isLock() {
		return lock;
	}
	
	public boolean isAccountLock() {
		return accountLock;
	}	
}