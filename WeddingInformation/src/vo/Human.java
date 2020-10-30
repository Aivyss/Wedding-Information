package vo;

public class Human {
	String name;
	String id;
	boolean sex;
	int LatestEduScore;
	String level;
	int password;
	int age;
	int salary;
	double bmi;
	
	public Human(String name, String id, boolean sex, int latestEduScore, String level, int password, int age,
			int salary, double bmi) {
		super();
		this.name = name;
		this.id = id;
		this.sex = sex;
		LatestEduScore = latestEduScore;
		this.level = level;
		this.password = password;
		this.age = age;
		this.salary = salary;
		this.bmi = bmi;
	}
	
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
	public int getLatestEduScore() {
		return LatestEduScore;
	}
	public void setLatestEduScore(int latestEduScore) {
		LatestEduScore = latestEduScore;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
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

	
	
}
