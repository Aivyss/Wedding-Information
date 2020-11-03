package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Female;
import vo.Human;
import vo.Male;

public class Rank {
	private Map<String, Human> humanMap;
	
	/**
	 * 생성자 오버로딩
	 * @param humanMap
	 */
	public Rank(Map<String, Human> humanMap) {
		this.humanMap = humanMap;
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
			giveScore(vo);
			humanMap.put(vo.getId(), vo);
			giveGrade();
			
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
		int latestEduScore = (vo.getLatestEduLevel() == 1) ? 60
						: (vo.getLatestEduLevel() == 2) ? 57
						: (vo.getLatestEduLevel() == 3) ? 54
						: (vo.getLatestEduLevel() == 4) ? 51 
						: (vo.getLatestEduLevel() == 5) ? 48 
						: (vo.getLatestEduLevel() == 6) ? 45 : 42;

		int salaryScore = (vo.getSalary() >= 1000000000) ? 100
						: (vo.getSalary() >= 600000000) ? 95
						: (vo.getSalary() >= 100000000) ? 90 
						: (vo.getSalary() >= 50000000) ? 85 : 80;

		int heightScore = (vo.getHeight() >= 180) ? 60
						: (vo.getHeight() >= 175) ? 58
						: (vo.getHeight() >= 170) ? 54
						: (vo.getHeight() >= 160) ? 52 
						: (vo.getHeight() >= 150) ? 50 : 48;
		
		vo.setLatestEduScore(latestEduScore);
		vo.setSalaryScore(salaryScore);
		vo.setHeightScore(heightScore);

		int totalScore;
		double normalizedScore;
		if (vo instanceof Female) {// 여성인 경우
			Female voF = (Female) vo;
			int ageScore = (vo.getAge() >= 35) ? 72 
						: (vo.getAge() >= 30) ? 76 
						: (vo.getAge() >= 25) ? 78 : 80;
			voF.setAgeScore(ageScore);
			totalScore = latestEduScore + salaryScore + heightScore + ageScore;
			normalizedScore = totalScore;

			vo = voF;
		} else { // 남성인 경우
			Male voM = (Male) vo;
			totalScore = latestEduScore + salaryScore + heightScore;
			normalizedScore = totalScore;

			if (voM.isTaco()) {
				normalizedScore = normalizedScore * 0.5;
			}
			
			vo = voM;
		}
		
		normalizedScore = (vo.getBmi() >= 25) ? normalizedScore * 0.85 // 비만
						: (vo.getBmi() >= 23) ? normalizedScore * 0.92 // 과체중
						: (vo.getBmi() >= 18.5) ? normalizedScore * 1.05 : normalizedScore * 0.95; // 정상 : 저체중
		vo.setNormalizedTotalScore(normalizedScore);
	}
	
	/**
	 * 등급을 부여하는 메소드
	 */	
	public void giveGrade() {
		List<Human> male = new ArrayList<>();
		List<Human> female = new ArrayList<>();
		Map<Integer, List<Human>> rank = new HashMap<>();
		
		// 여자/남자를 분류하는 프로세스
		for (String id : humanMap.keySet()) {
			if (humanMap.get(id) instanceof Male) {
				male.add(humanMap.get(id));
			} else {
				female.add(humanMap.get(id));
			}
		}
		rank.put(0, male);
		rank.put(1, female);
		
		// 여자 남자를 점수 순으로 정렬하는 프로세스
		for (Integer i : rank.keySet()) {
			for (int j = 0; j < rank.get(i).size() - 1; j++) {

				for (int k = j+1; k < rank.get(i).size(); k++) {
					if (rank.get(i).get(j).getNormalizedTotalScore() < rank.get(i).get(k).getNormalizedTotalScore()) {
						Human temp = null;
						temp = rank.get(i).get(j);
						rank.get(i).set(j, rank.get(i).get(k));
						rank.get(i).set(k, temp);
					}
				}
			}
		}
		
		// 정렬된 것에 따라 랭크를 부여하는 프로세스
		for (Integer i : rank.keySet()) {
			for (int j = rank.get(i).size()-1; j>=0; j--) {
				int level = (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.84) ? 0 // 언랭
							: (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.7) ? 1 // 브론즈
							: (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.56) ? 2 // 실버
							: (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.42) ? 3 // 골드
							: (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.28) ? 4 // 플래티넘
							: (((j + 1) * 1.0) / (rank.get(i).size() * 1.0) > 0.14) ? 5 : 6; // 다이아: 비브라늄
				rank.get(i).get(j).setLevel(level);
				
				
				String grade = (rank.get(i).get(j).getLevel() == 0) ? "언랭"
								: (rank.get(i).get(j).getLevel() == 1) ? "브론즈"
								: (rank.get(i).get(j).getLevel() == 2) ? "실버"
								: (rank.get(i).get(j).getLevel() == 3) ? "골드"
								: (rank.get(i).get(j).getLevel() == 4) ? "플래티넘"
								: (rank.get(i).get(j).getLevel() == 5) ? "다이아" : "비브라늄";
				rank.get(i).get(j).setGrade(grade);
			}
		}
	}
}
