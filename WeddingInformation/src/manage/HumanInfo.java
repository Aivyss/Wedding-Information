package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dao.WeddingDAO;
import vo.Female;
import vo.Human;
import vo.Male;

public class HumanInfo {
	private Random rd;
	private Map<String, Human> humanMap; // <id, vo>
	private static HumanInfo humanInfo = new HumanInfo();

	/**
	 * 생성자
	 */
	private HumanInfo() {
		humanMap = new HashMap<>();
		rd = new Random();
		WeddingDAO dao = new WeddingDAO();
		
		if (dao.getAll() != null && !dao.getAll().isEmpty()) {
			for (Human vo : dao.getAll()) {
				humanMap.put(vo.getId(), vo);
			}
		}
	}
	
	/**
	 * HumanInfo 객체를 얻는 메소드
	 */
	public static HumanInfo getInstance() {
		return humanInfo;
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
		vo = searchAccount(id);

		if (vo != null) {
			if (vo.getLockCount() < 3) {
				if (!pw.equals(vo.getPassword())) { 
					// 비밀번호를 틀려 계정락 카운트를 늘리고 로그인 실패
					vo.setLockCount(vo.getLockCount() + 1);
					vo = null;
				}
			} else { 
				// 비밀번호를 3회 이상 틀려 계정락상태, 로그인 실패
				vo = null;
			}
		}

		if (vo != null) { 
			// 로그인에 성공한 경우 락카운트 초기화
			vo.setLockCount(0);
		}

		return vo;
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
	 * 새로운 가입자의 정보를 회원목록에 추가하는 메소드
	 * 
	 * @param vo
	 * @return 가입이 정상적으로 처리되면 true를 반환
	 */
	public boolean addAccount(Human vo) {
		Rank rank = Rank.getInstance();

		return rank.addAccount(vo);
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
	public Human searchMatch(int level, Human vo) {
		List<Human> list = new ArrayList<>();
		Human matched = null;

		if (vo instanceof Male) {
			for (String id : humanMap.keySet()) {
				if (humanMap.get(id) instanceof Female) {
					list.add(humanMap.get(id));
				}
			}
		} else {
			for (String id : humanMap.keySet()) {
				if (humanMap.get(id) instanceof Male) {
					list.add(humanMap.get(id));
				}
			}
		}

		if (list.size() == 0) {
			matched = null;
		} else { // 사이즈 1 이상
			int index = rd.nextInt(list.size());
			matched = list.get(index);
		}

		return matched;
	}

	/**
	 * 매칭요청을 수행하는 메소드
	 * 
	 * @param me
	 * @param you
	 * @return
	 */
	public boolean match(String myId, String yourId) {
		boolean flag = false;
		Human me = humanMap.get(myId);
		Human you = humanMap.get(yourId);

		if (me != null && you != null) {
			me.setMatchedId(you.getId());
			me.setInvited(false);
			me.setLock(true);

			you.setMatchedId(me.getId());
			you.setInvited(true);
			you.setLock(true);

			flag = true;
		}

		return flag;
	}

	/**
	 * 상대의 매칭을 수락/거부하는 메소드
	 */
	public boolean accept(String myId, String yourId, boolean flag) {
		Human me = humanMap.get(myId);
		Human you = humanMap.get(yourId);
		boolean flagT = false;

		if (me.isInvited() && !me.isSuccess()) {
			int gradeIndex = me.getGradeIndex();

			int fee = (gradeIndex == 0) ? 100000
					: (gradeIndex == 1) ? 500000
					: (gradeIndex == 2) ? 1000000
					: (gradeIndex == 3) ? 2000000
					: (gradeIndex == 4) ? 5000000 
					: (gradeIndex == 5) ? 7000000 : 10000000;

			if (flag) { // 매칭의사 yes
				if (you.getCash() - fee >= 0) { // 매칭이 성사
					you.setCash(you.getCash() - fee);
					you.setInvited(true);
					you.setSuccess(true);
					me.setSuccess(true);

					flagT = true;
				} else { // 돈이 없어 매칭이 실패
					initialize(myId);
				}
			} else { // 매칭의사 no
				initialize(myId);
			}
		}

		return flagT;
	}

	/**
	 * 매칭 정보를 초기화 해주는 메소드. 다시 만남을 가질 수 있도록 해준다.
	 * 
	 * @param id
	 * @return
	 */
	public boolean initialize(String id) {
		boolean flag = false;
		Human me = searchAccount(id);
		Human you = searchAccount(me.getMatchedId());

		if (me != null && you != null) {
			me.setMatchedId(null);
			me.setInvited(false);
			me.setLock(false);
			you.setMatchedId(null);
			you.setInvited(false);
			you.setLock(false);
			you.setSuccess(false);
			me.setSuccess(false);

			flag = true;
		}

		return flag;
	}

	/**
	 * 회원 탈퇴를 진행하는 메소드 회원탈퇴가 성공하면 true 실패하면 false
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeAccount(String id, String pw) {
		boolean flag = false;

		if (pw.equals(humanMap.get(id).getPassword())) {
			initialize(id); // 매칭 상대가 있다면 매칭 상대도 상태를 반영해야 하므로 시행
			humanMap.remove(id);
			Rank rank = Rank.getInstance();
			rank.giveGrade(humanMap.get(id)); // 삭제된 회원에따른 등급 재배치
			flag = true;
		}

		return flag;
	}

	public Map<String, Human> getHumanMap() {
		return humanMap;
	}

}
