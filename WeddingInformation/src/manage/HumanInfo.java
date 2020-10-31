package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import vo.Female;
import vo.Human;
import vo.Male;

public class HumanInfo {
	Random rd;
	public Map<String, Human> humanMap; // <id, vo>

	/**
	 * 생성자
	 */
	public HumanInfo() {
		humanMap = new HashMap<>();
		rd = new Random();
	}

	/**
	 * 로그인을 수행하는 메소드이다.
	 * 
	 * @param id
	 * @param pw
	 * @return 로그인이 되면 객체반환 실패하면 null 반환
	 */
	public Human signIn(String id, String pw) {
		Human vo = null;
		vo = humanMap.get(id);

		if (vo != null) {
			if (!pw.equals(vo.getPassword())) {
				vo = null;
			}
		}

		return vo;
	}

	/**
	 * 새로운 가입자의 정보를 회원목록에 추가하는 메소드
	 * 
	 * @param vo
	 * @return 가입이 정상적으로 처리되면 true를 반환
	 */
	public boolean addAccount(Human vo) {
		boolean flag = true;

		if (vo != null) {
			humanMap.put(vo.getId(), vo);
		} else {
			flag = false;
		}

		return flag;
	}

	/**
	 * id로 회원을 찾아서 vo객체를 반환하는 메소드
	 * 
	 * @param id
	 * @return 회원을 찾지 못할시에는 null을 반환함
	 */
	public Human searchAccount(String id) {
		return humanMap.get(id);
	}

	/**
	 * 등급을 부여하는 메소드
	 */
	public void giveGrade() {
		List<Human> rankMale = new ArrayList<Human>();
		List<Human> rankFemale = new ArrayList<Human>();
		
		for (String id : humanMap.keySet()) {
			if (humanMap.get(id).isSex()) {
				rankMale.add(humanMap.get(id));
			} else {
				rankFemale.add(humanMap.get(id));
			}
		}

		// 남성 랭크 정렬
		for (int i = 0; i < rankMale.size()-1; i++) {
			Human temp = null;

			for (int j = i; j < rankMale.size(); j++) {
				if (rankMale.get(i).getNomalizedTotalScore() < rankMale.get(j).getNomalizedTotalScore()) {
					temp = rankMale.get(i);
					rankMale.set(i, rankMale.get(j));
					rankMale.set(j, temp);
				}
			}
		}

		// 여성 랭크 정렬
		for (int i = 0; i < rankFemale.size()-1; i++) {
			Human temp = null;

			for (int j = i; j < rankMale.size(); j++) {
				if (rankFemale.get(i).getNomalizedTotalScore() < rankFemale.get(j).getNomalizedTotalScore()) {
					temp = rankFemale.get(i);
					rankFemale.set(i, rankFemale.get(j));
					rankFemale.set(j, temp);
				}
			}
		}

		// 남성 랭크 부여
		for (int i = rankMale.size() - 1; i >= 0; i--) {
			if ((i * 1.0) / (rankMale.size() * 1.0) > 0.84) {
				rankMale.get(i).setLevel(0); // 언랭
			} else if ((i * 1.0) / (rankMale.size() * 1.0) > 0.7) {
				rankMale.get(i).setLevel(1); // 브론즈
			} else if ((i * 1.0) / (rankMale.size() * 1.0) > 0.56) {
				rankMale.get(i).setLevel(2); // 실버
			} else if ((i * 1.0) / (rankMale.size() * 1.0) > 0.42) {
				rankMale.get(i).setLevel(3); // 골드
			} else if ((i * 1.0) / (rankMale.size() * 1.0) > 0.28) {
				rankMale.get(i).setLevel(4); // 플래티넘
			} else if ((i * 1.0) / (rankMale.size() * 1.0) > 0.14) {
				rankMale.get(i).setLevel(5); // 다이아
			} else {
				rankMale.get(i).setLevel(6); // 비브라늄
			}
		}

		// 여성 랭크 부여
		for (int i = rankFemale.size() - 1; i >= 0; i--) {
			if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.84) {
				rankFemale.get(i).setLevel(0); // 언랭
			} else if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.7) {
				rankFemale.get(i).setLevel(1); // 브론즈
			} else if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.56) {
				rankFemale.get(i).setLevel(2); // 실버
			} else if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.42) {
				rankFemale.get(i).setLevel(3); // 골드
			} else if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.28) {
				rankFemale.get(i).setLevel(4); // 플래티넘
			} else if ((i * 1.0) / (rankFemale.size() * 1.0) > 0.14) {
				rankFemale.get(i).setLevel(5); // 다이아
			} else {
				rankFemale.get(i).setLevel(6); // 비브라늄
			}
		}
		
		// 남성 업데이트
		for (Human male : rankMale) {
			humanMap.put(male.getId(), male);
		}
		
		// 여성 업데이트
		for (Human female : rankFemale) {
			humanMap.put(female.getId(), female);
		}
	}
	
	/**
	 * 회원의 점수를 매기는 프로세스
	 */
	public void giveScore(Human vo) {
		// 공통 점수를 매기는 프로세스
		int eduIndex = vo.getLatestEduScore();
		int eduScore = (eduIndex == 1) ? 60 : 
						(eduIndex == 2) ? 57 : 
						(eduIndex == 3) ? 54 : 
						(eduIndex == 4) ? 51 : 
						(eduIndex == 5) ? 48 : 
						(eduIndex == 6) ? 45 : 42;
		
		int salaryScore = (vo.getSalary() >= 1000000000) ? 100 : 
							(vo.getSalary() >= 600000000) ? 95 :
							(vo.getSalary() >= 100000000) ? 90 :
							(vo.getSalary() >= 50000000) ? 85 : 80;
		
		
		int heightScore = (vo.getHeight() >= 180) ? 60 : 
							(vo.getHeight() >= 175) ? 58 :
							(vo.getHeight() >= 170) ? 54 : 
							(vo.getHeight() >= 160) ? 52 :
							(vo.getHeight() >= 150) ?  50 : 48;
		
		vo.setLatestEduScore(eduScore);
		vo.setSalaryScore(salaryScore);
		vo.setHeightScore(heightScore);
		
		if (!vo.isSex()) {// 여성인 경우
			Female voF = (Female) vo;
			int ageScore = (vo.getAge() >= 35) ? 72 : (vo.getAge() >= 30) ? 76 : (vo.getAge() >= 25) ? 78 : 80;
			voF.setAgeScore(ageScore);
			int totalScore = eduScore + salaryScore + heightScore + ageScore;
			double normalizedScore = totalScore;
			voF.setTotalScore(totalScore);
			
			normalizedScore = (voF.getSurgeryScore() == 1) ? totalScore*0.5 : 
								(voF.getSurgeryScore() == 2) ? totalScore*0.7 : 
								(voF.getSurgeryScore() == 3) ? totalScore*0.9 : totalScore;  
			
			voF.setNomalizedTotalScore(normalizedScore);
			humanMap.put(voF.getId(), voF);
		} else { // 남성인 경우
			Male voM = (Male) vo;
			int totalScore = eduScore + salaryScore + heightScore;
			double normalizedScore = totalScore;
			voM.setTotalScore(totalScore);
			
			if(voM.isTaco()) {
				normalizedScore = totalScore*0.5;
			}
			
			voM.setNomalizedTotalScore(normalizedScore);
			humanMap.put(voM.getId(), voM);
		}
		
		giveGrade();
	}

	/**
	 * 로그인한 고객이 금액을 충전할 수 있도록 수행하는 메소드
	 * 
	 * @param vo
	 * @param pw
	 * @param cash
	 * @return
	 */
	public boolean deposit(Human vo, String pw, int cash) {
		boolean flag = false;
		String id = vo.getId();

		if (pw.equals(vo.getPassword())) {
			searchAccount(id).setCash(searchAccount(id).getCash() + cash);
			flag = true;
		}

		return flag;
	}

	/**
	 * 지정한 레벨인 대상자를 랜덤으로 뽑아내는 메소드 리스트로 해당자만 담아서 그 안에서 랜덤으로 추출한다.
	 * 
	 * @param level
	 * @return
	 */
	public Human searchMatch(int level, boolean sex) {
		List<Human> list = new ArrayList<>();
		Human vo = null;

		for (String id : humanMap.keySet()) {
			if (humanMap.get(id).getLevel() == level && sex != humanMap.get(id).isSex()) {
				list.add(humanMap.get(id));
			}
		}
		
		if(list.size() == 0) {
			vo = null;
		} else { // 사이즈 1 이상
			int index = rd.nextInt(list.size());
			vo = list.get(index);
		}

		return vo;
	}

	public boolean match(Human me, Human you) {
		boolean flag = false;
		String meId = me.getId();
		String youId = you.getId();

		if (me != null && you != null) {
			me.setMatchedId(you.getId());
			me.setInvited(false);
			me.setLock(true);

			you.setMatchedId(me.getId());
			you.setInvited(true);
			you.setLock(true);

			humanMap.put(meId, me);
			humanMap.put(youId, you);
			flag = true;
		}

		return flag;
	}
	
	/**
	 * 상대의 매칭을 수락하는 메소드
	 */
	public boolean accept(String myId, String yourId, boolean flag) {
		Human me = humanMap.get(myId);
		Human you = humanMap.get(yourId);
		boolean flagT = false;
		
		int level = me.getLevel();
		
		int fee = (level == 0) ? 100000 : 
					(level == 1) ? 500000 : 
					(level == 2) ? 1000000 : 
					(level == 3) ? 2000000 :
					(level == 4) ? 5000000 : 
					(level == 5) ? 7000000 : 10000000;
		
		if(flag) {
			if (you.getCash()-fee >= 0) {
				you.setCash(you.getCash()-fee);
				you.setInvited(true);
				you.setSuccess(true);
				me.setSuccess(true);
				
				flagT = true;
			} else {
				me.setMatchedId(null);
				me.setInvited(false);
				me.setLock(false);
				you.setMatchedId(null);
				you.setInvited(false);
				you.setLock(false);
			}
		} else {
			me.setMatchedId(null);
			me.setInvited(false);
			me.setLock(false);
			you.setMatchedId(null);
			you.setInvited(false);
			you.setLock(false);
		}
		
		
		return flagT;
	}
}
