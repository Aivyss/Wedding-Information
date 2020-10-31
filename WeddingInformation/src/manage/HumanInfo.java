package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import vo.Female;
import vo.Human;

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

		Human[] rank = new Human[rankMale.size()];

		for (String id : humanMap.keySet()) {
			if (humanMap.get(id).isSex()) {
				rankMale.add(humanMap.get(id));
			} else {
				rankFemale.add(humanMap.get(id));
			}
		}

		// 남성 랭크 정렬
		for (int i = 0; i < rankMale.size(); i++) {
			Human temp = null;
			double max = 0;
			int jMax = 0;

			for (int j = i; i < rankMale.size(); j++) {
				if (max > rankMale.get(i).getNomalizedTotalScore()) {
					max = rankMale.get(i).getNomalizedTotalScore();
					jMax = j;
				}
			}

			temp = rankMale.get(i);
			rankMale.set(i, rankMale.get(jMax));
			rankMale.set(jMax, temp);
		}

		// 여성 랭크 정렬
		for (int i = 0; i < rankFemale.size(); i++) {
			Human temp = null;
			double max = 0;
			int jMax = 0;

			for (int j = i; i < rankFemale.size(); j++) {
				if (max > rankFemale.get(i).getNomalizedTotalScore()) {
					max = rankFemale.get(i).getNomalizedTotalScore();
					jMax = j;
				}
			}

			temp = rankFemale.get(i);
			rankFemale.set(i, rankFemale.get(jMax));
			rankFemale.set(jMax, temp);
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
	 * 여성회원의 점수를 매기는 프로세스
	 * 
	 * @param voF
	 */
	public void giveFemaleScore(Female voF) {
		// 나이에 따른 점수부여
		if (voF.getAge() >= 35) {
			voF.setAgeScore(72);
		} else if (voF.getAge() >= 30) {
			voF.setAgeScore(76);
		} else if (voF.getAge() >= 25) {
			voF.setAgeScore(78);
		} else {
			voF.setAgeScore(80);
		}

		// 성형에 따른 감점 처리
		int totalScore = voF.getTotalScore();

		if (voF.getSurgeryScore() == 1) {
			voF.setNomalizedTotalScore(totalScore * 0.5);
		} else if (voF.getSurgeryScore() == 2) {
			voF.setNomalizedTotalScore(totalScore * 0.7);
		} else if (voF.getSurgeryScore() == 3) {
			voF.setNomalizedTotalScore(totalScore * 0.9);
		} else if (voF.getSurgeryScore() == 4) {
			voF.setNomalizedTotalScore(totalScore);
		}
		humanMap.put(voF.getId(), voF);
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

		for (String id : humanMap.keySet()) {
			if (humanMap.get(id).getLevel() == level && sex != humanMap.get(id).isSex()) {
				list.add(humanMap.get(id));
			}
		}

		int index = rd.nextInt(list.size());

		return list.get(index);
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
}
