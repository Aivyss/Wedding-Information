package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vo.Female;
import vo.Human;

public class HumanInfo {
	public Map<String, Human> humanMap; // <id, vo>
	
	/**
	 * 생성자
	 */
	public HumanInfo() {
		humanMap = new HashMap<>();
	}
	
	/**
	 * 로그인을 수행하는 메소드이다.
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
		int numOfPeople = humanMap.keySet().size();
		Human[] rank = new Human[numOfPeople];
		
		int index = 0;
		for (String id : humanMap.keySet()) {
			rank[index] = humanMap.get(id);
			index++;
		}
		
		for (int i=0; i<rank.length; i++) {
			Human temp = null;
			double max = 0;
			int jMax = 0;
			
			for (int j=i; i<rank.length; j++) {
				if(max > rank[j].getNomalizedTotalScore()) {
					max = rank[j].getNomalizedTotalScore();
					jMax = j;
				}
			}
			
			temp = rank[i];
			rank[i] = rank[jMax];
			rank[jMax] = temp;
		}
		
		for(int i=rank.length-1; i>=0; i--) {
			if ((i*1.0)/(numOfPeople*1.0) > 0.84) {
				rank[i].setLevel("언랭");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.7) {
				rank[i].setLevel("브론즈");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.56) {
				rank[i].setLevel("실버");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.42) {
				rank[i].setLevel("골드");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.28) {
				rank[i].setLevel("플래티넘");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.14) {
				rank[i].setLevel("다이아");
			} else {
				rank[i].setLevel("비브라늄");
			}
		}
	}
	
	/**
	 * 여성회원의 점수를 매기는 프로세스
	 * @param voF
	 */
	public void giveFemaleScore(Female voF) {
		// 나이에 따른 점수부여
		if (voF.getAge() >= 35) {
			voF.setAgeScore(72);
		} else if (voF.getAge() >=30) {
			voF.setAgeScore(76);
		} else if (voF.getAge() >=25) {
			voF.setAgeScore(78);
		} else {
			voF.setAgeScore(80);
		}
		
		// 성형에 따른 감점 처리
		int totalScore = voF.getTotalScore();
		
		if (voF.getSurgeryScore() == 1) {
			voF.setNomalizedTotalScore(totalScore*0.5);
		} else if (voF.getSurgeryScore() == 2) {
			voF.setNomalizedTotalScore(totalScore*0.7);
		} else if (voF.getSurgeryScore() == 3) {
			voF.setNomalizedTotalScore(totalScore*0.9);
		} else if (voF.getSurgeryScore() == 4) {
			voF.setNomalizedTotalScore(totalScore);
		}
		
		humanMap.put(voF.getId(), voF);
	}
	
	public Human match(Human vo) {
		Human another = null;
		
		return another;
	}
}
