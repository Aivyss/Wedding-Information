package manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vo.Female;
import vo.Human;
import vo.Male;

public class HumanInfo {
	Random rd;
	public Map<String, Human> humanMap; // <id, vo>

	/**
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

		return new Rank(humanMap).addAccount(vo);
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
	 * 상대의 매칭을 수락하는 메소드
	 */
	public boolean accept(String myId, String yourId, boolean flag) {
		Human me = humanMap.get(myId);
		Human you = humanMap.get(yourId);
		boolean flagT = false;

		if (me.isInvited() && !me.isSuccess()) {
			int level = me.getLevel();

			int fee = (level == 0) ? 100000
					: (level == 1) ? 500000
					: (level == 2) ? 1000000
					: (level == 3) ? 2000000
					: (level == 4) ? 5000000 
					: (level == 5) ? 7000000 : 10000000;

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
}
