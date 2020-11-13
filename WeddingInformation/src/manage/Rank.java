package manage;

import java.util.List;

import dao.WeddingDAO;
import vo.Female;
import vo.Human;
import vo.Male;

public class Rank {
	private static final Rank rank = new Rank();
	private HumanInfo humanInfo = HumanInfo.getInstance();
	private final WeddingDAO dao = new WeddingDAO();
	
	/**
	 * 생성자
	 */
	private Rank() {
		
	}
	/**
	 * Rank 객체를 얻는 메소드
	 */
	public static Rank getInstance() {
		return rank;
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
			humanInfo.getHumanMap().put(vo.getId(), vo);
			dao.addAccount(vo);
			giveGrade(vo);
			
			// 제대로 값이 들어갔는지 확인하는 조건문
			if (humanInfo.getHumanMap().get(vo.getId()) != null ) {  // && vo.getGrade() != null
				flag = true;
			}
		}

		return flag;
	}
	
	/**
	 * 회원의 점수를 매기는 메소드
	 */
	private void giveScore(Human vo) {
		// 공통 점수를 매기는 프로세스
		int latestEduScore = (vo.getLatestEduIndex() == 1) ? 60
						: (vo.getLatestEduIndex() == 2) ? 57
						: (vo.getLatestEduIndex() == 3) ? 54
						: (vo.getLatestEduIndex() == 4) ? 51 
						: (vo.getLatestEduIndex() == 5) ? 48 
						: (vo.getLatestEduIndex() == 6) ? 45 : 42;

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
		
		// 가중 환산점수로 바꾸는 프로세스
		int totalScore = latestEduScore + salaryScore + heightScore;
		double normalizedScore = 0;
		if (vo instanceof Female) {// 여성인 경우
			Female voF = (Female) vo;
			int ageScore = (voF.getAge() >= 35) ? 72 
						: (voF.getAge() >= 30) ? 76 
						: (voF.getAge() >= 25) ? 78 : 80;
			voF.setAgeScore(ageScore);
			
			totalScore += ageScore;
			normalizedScore = totalScore;
			normalizedScore *= (voF.getSurgery() == 4) ? 1.0 
							: (voF.getSurgery() == 3) ? 0.9
							: (voF.getSurgery() == 2) ? 0.7 : 0.5;

			vo = voF;
		} else { // 남성인 경우
			Male voM = (Male) vo;
			totalScore = latestEduScore + salaryScore + heightScore;
			normalizedScore = totalScore;
			
			normalizedScore *= (voM.isTaco()) ? 0.5 : 1.0;
			
			vo = voM;
		}
		
		normalizedScore *= (vo.getBmi() >= 25) ? 0.85 // 비만
						: (vo.getBmi() >= 23) ? 0.92 // 과체중
						: (vo.getBmi() >= 18.5) ? 1.05 : 0.95; // 정상 : 저체중
		vo.setTotalScore(totalScore);
		vo.setNormalizedTotalScore(normalizedScore);
	}
	
	/**
	 * 등급을 부여하는 메소드
	 */	
	public void giveGrade(Human vo) {
		List<Human> list = dao.getList(vo);
		
		// 정렬된 것에 따라 랭크를 부여하는 프로세스
		for (int j = list.size()-1; j>=0; j--) {
			int level = (((j + 1) * 1.0) / (list.size() * 1.0) > 0.84) ? 0 // 언랭
						: (((j + 1) * 1.0) / (list.size() * 1.0) > 0.7) ? 1 // 브론즈
						: (((j + 1) * 1.0) / (list.size() * 1.0) > 0.56) ? 2 // 실버
						: (((j + 1) * 1.0) / (list.size() * 1.0) > 0.42) ? 3 // 골드
						: (((j + 1) * 1.0) / (list.size() * 1.0) > 0.28) ? 4 // 플래티넘
						: (((j + 1) * 1.0) / (list.size() * 1.0) > 0.14) ? 5 : 6; // 다이아: 비브라늄
			list.get(j).setGradeIndex(level);
			
			
			String grade = (list.get(j).getGradeIndex() == 0) ? "언랭"
							: (list.get(j).getGradeIndex() == 1) ? "브론즈"
							: (list.get(j).getGradeIndex() == 2) ? "실버"
							: (list.get(j).getGradeIndex() == 3) ? "골드"
							: (list.get(j).getGradeIndex() == 4) ? "플래티넘"
							: (list.get(j).getGradeIndex() == 5) ? "다이아" : "비브라늄";
			list.get(j).setGrade(grade);
			
			dao.updateGrade(list.get(j));
			
		}
	}
}
