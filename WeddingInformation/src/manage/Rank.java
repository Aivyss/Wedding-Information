package manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vo.Female;
import vo.Human;
import vo.Male;

public class Rank {
	List<Human> male;
	List<Human> female;
	Map<String, Human> humanMap;
	public int length;
	
	/**
	 * 생성자 오버로딩
	 * @param humanMap
	 */
	public Rank(Map<String, Human> humanMap) {
		male = new ArrayList<>();
		female = new ArrayList<>();
		length = 2;
		this.humanMap = humanMap;
	}
	
	/**
	 * 남성인지 여성인지 분류하여 각각의 어레이리스트에 넣는 메소드
	 * @param index
	 * @param vo
	 */
	public void add(int index, Human vo) {
		if (index == 0) {
			male.add(vo);
		} else {
			female.add(vo);
		}
	}
	
	/**
	 * 남성, 여성이 분류된 어레이리스트에서 값을 추출하는 메소드
	 * @param index
	 * @param listIndex
	 * @return
	 */
	public Human get(int index, int listIndex) {
		Human result = null;
		
		if (index == 0) {
			result = male.get(listIndex);
		} else if (index == 1) {
			result = female.get(listIndex);
		}
		
		return result; 
	}
	
	/**
	 * 어레이리스트의 값을 다른 vo로 교체하는 메소드
	 * @param index
	 * @param listIndex
	 * @param vo
	 */
	public void set(int index, int listIndex, Human vo) {
		if (index == 0) {
			male.set(listIndex, vo);
		} else {
			female.set(listIndex, vo);
		}
	}
	
	/**
	 * 각 어레이리스트의 사이즈를 추출하는 메소드
	 * @param index
	 * @return
	 */
	public int size(int index) {
		int size = female.size();
		
		if (index == 0) {
			size = male.size();
		} 
		
		return size;
	}
	
	/**
	 * 새로운 가입자의 정보를 회원목록에 추가하는 메소드
	 * 
	 * @param vo
	 * @return 가입이 정상적으로 처리되면 true를 반환
	 */
	public boolean addAccount(Human vo) {
		boolean flag = false;

		if (vo != null) {
			humanMap.put(vo.getId(), vo);
			giveScore(vo);
			
			// 제대로 값이 들어갔는지 확인하는 조건문
			if (humanMap.get(vo.getId()) != null && vo.getGrade() != null) { 
				
				flag = true;
			}
		}

		return flag;
	}
	
	/**
	 * 회원의 점수를 매기는 메소드
	 */
	public void giveScore(Human vo) {
		// 공통 점수를 매기는 프로세스
		int eduIndex = vo.getLatestEduScore();
		int eduScore = (eduIndex == 1) ? 60
						: (eduIndex == 2) ? 57
						: (eduIndex == 3) ? 54
						: (eduIndex == 4) ? 51 
						: (eduIndex == 5) ? 48 
						: (eduIndex == 6) ? 45 : 42;

		int salaryScore = (vo.getSalary() >= 1000000000) ? 100
						: (vo.getSalary() >= 600000000) ? 95
						: (vo.getSalary() >= 100000000) ? 90 
						: (vo.getSalary() >= 50000000) ? 85 : 80;

		int heightScore = (vo.getHeight() >= 180) ? 60
						: (vo.getHeight() >= 175) ? 58
						: (vo.getHeight() >= 170) ? 54
						: (vo.getHeight() >= 160) ? 52 
						: (vo.getHeight() >= 150) ? 50 : 48;

		vo.setLatestEduScore(eduScore);
		vo.setSalaryScore(salaryScore);
		vo.setHeightScore(heightScore);

		if (vo instanceof Female) {// 여성인 경우
			Female voF = (Female) vo;
			int ageScore = (vo.getAge() >= 35) ? 72 
						: (vo.getAge() >= 30) ? 76 
						: (vo.getAge() >= 25) ? 78 : 80;
			voF.setAgeScore(ageScore);
			int totalScore = eduScore + salaryScore + heightScore + ageScore;
			double normalizedScore = totalScore;
			voF.setTotalScore(totalScore);

			normalizedScore = (voF.getsurgery() == 1) ? totalScore * 0.5
							: (voF.getsurgery() == 2) ? totalScore * 0.7
							: (voF.getsurgery() == 3) ? totalScore * 0.9 : totalScore;

			voF.setNomalizedTotalScore(normalizedScore);
		} else { // 남성인 경우
			Male voM = (Male) vo;
			int totalScore = eduScore + salaryScore + heightScore;
			double normalizedScore = totalScore;
			voM.setTotalScore(totalScore);

			if (voM.isTaco()) {
				normalizedScore = totalScore * 0.5;
			}

			voM.setNomalizedTotalScore(normalizedScore);
		}

		giveGrade();
	}
	
	/**
	 * 등급을 부여하는 메소드
	 */	
	public void giveGrade() {
		// 여자/남자를 분류하는 프로세스
		for (String id : humanMap.keySet()) {
			if (humanMap.get(id) instanceof Male) {
				add(0, humanMap.get(id));
			} else {
				add(1, humanMap.get(id));
			}
		}
		
		// 여자 남자를 점수 순으로 정렬하는 프로세스
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.size(i) - 1; j++) {
				Human temp = null;

				for (int k = 0; k < size(i); k++) {
					if (get(i, j).getNomalizedTotalScore() < get(i, k).getNomalizedTotalScore()) {
						temp = get(i, j);
						set(i ,j, get(i, k));
						set(i, k, temp);
					}
				}
			}
		}
		
		// 정렬된 것에 따라 랭크를 부여하는 프로세스
		for (int i = 0; i < length; i++) {
			for (int j = size(i)-1; j>=0; j--) {
				if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.84) {
					get(i, j).setLevel(0); // 언랭
				} else if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.7) {
					get(i, j).setLevel(1); // 브론즈
				} else if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.56) {
					get(i, j).setLevel(2); // 실버
				} else if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.42) {
					get(i, j).setLevel(3); // 골드
				} else if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.28) {
					get(i, j).setLevel(4); // 플래티넘
				} else if (((j + 1) * 1.0) / (size(i) * 1.0) > 0.14) {
					get(i, j).setLevel(5); // 다이아
				} else {
					get(i, j).setLevel(6); // 비브라늄
				}
				
				String grade = (get(i, j).getLevel() == 0) ? "언랭"
								: (get(i, j).getLevel() == 1) ? "브론즈"
								: (get(i, j).getLevel() == 2) ? "실버"
								: (get(i, j).getLevel() == 3) ? "골드"
								: (get(i, j).getLevel() == 4) ? "플래티넘"
								: (get(i, j).getLevel() == 5) ? "다이아" : "비브라늄";

				get(i, j).setGrade(grade);
			}
		}
	}
}
