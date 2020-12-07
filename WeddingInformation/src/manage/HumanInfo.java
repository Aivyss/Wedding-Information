package manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.WeddingDAO;
import vo.Human;

public class HumanInfo {
	private Random rd;
	private WeddingDAO dao;
	private static HumanInfo humanInfo = new HumanInfo();

	/**
	 * 생성자
	 */
	private HumanInfo() {
		rd = new Random();
		dao = new WeddingDAO();
	}

	/**
	 * HumanInfo 객체를 얻는 메소드
	 */
	public static HumanInfo getInstance() {
		return humanInfo;
	}

	/**
	 * (완료) 로그인을 수행하는 메소드이다.
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
					dao.updateLockCount(vo);
					vo = null;
				}
			} else {
				// 비밀번호를 3회 이상 틀려 계정락상태, 로그인 실패
				vo = null;
			}
		}

		if (vo != null) {
			// 로그인에 성공한 경우 락카운트 초기화 및 등급이름 호출
			vo.setLockCount(0);
			dao.updateLockCount(vo);
		}

		return vo;
	}

	/**
	 * (완료) id로 회원을 찾아서 vo객체를 반환하는 메소드
	 * 
	 * @param id
	 * @return 회원을 찾지 못할시에는 null을 반환함
	 */
	public Human searchAccount(String id) {
		return dao.searchAccount(id);
	}

	/**
	 * 새로운 가입자의 정보를 회원목록에 추가하는 메소드
	 * 
	 * @param vo
	 * @return 가입이 정상적으로 처리되면 true를 반환
	 */
	public boolean addAccount(Human vo) {
		boolean flag = false;

		flag = (dao.searchAccount(vo.getId()) == null) ? dao.addAccount(vo) : false;

		return flag;
	}

	/**
	 * (완료) 로그인한 고객이 금액을 충전할 수 있도록 수행하는 메소드
	 * 
	 * @param vo
	 * @param pw
	 * @param cash
	 * @return 입금성공여부
	 */
	public boolean deposit(Human vo, String pw, int cash) {
		boolean flag = false;

		if (pw.equals(vo.getPassword())) {
			vo.setCash(vo.getCash() + cash);
			dao.updateCash(vo);

			flag = true; // 입금처리 성공
		}

		return flag;
	}

	/**
	 * (완료) 지정한 레벨인 대상자를 랜덤으로 뽑아내는 메소드 리스트로 해당자만 담아서 그 안에서 랜덤으로 추출한다.
	 * 
	 * @param level
	 * @return
	 */
	public Human searchMatch(int gradeIndex, Human vo) {
		List<Human> list = null;
		List<Human> candidate = new ArrayList<Human>();
		Human matched = new Human();

		if (vo.getSex() == 1) {
			matched.setSex(0);
		} else {
			matched.setSex(1);
		}

		list = dao.getList(matched); // 이성을 골라냄

		for (Human vvo : list) {
			if (vvo.getGradeIndex() == gradeIndex) {
				candidate.add(vvo); // 같은 등급만 골라냄
			}
		}

		if (candidate.size() == 0) {
			matched = null;
		} else { // 사이즈 1 이상
			int index = rd.nextInt(candidate.size());
			matched = candidate.get(index);
		}

		return matched;
	}

	/**
	 * (완료) 매칭요청을 수행하는 메소드
	 * 
	 * @param me
	 * @param you
	 * @return
	 */
	public boolean match(String myId, String yourId) {
		boolean flag = false;
		Human me = dao.searchAccount(myId);
		Human you = dao.searchAccount(yourId);

		if (me != null && you != null) {
			if (me.getMatchLock() == 0 && you.getMatchLock() == 0) {
				me.setMatchedId(you.getId());
				me.setInvited(0);
				me.setMatchLock(1);
				dao.updateLockAndMatch(me);

				you.setMatchedId(me.getId());
				you.setInvited(1);
				you.setMatchLock(1);
				dao.updateLockAndMatch(you);

				flag = true;
			}
		}

		return flag;
	}

	/**
	 * 상대의 매칭을 수락/거부하는 메소드
	 */
	public boolean accept(String myId, String yourId, boolean flag) {
		Human me = dao.searchAccount(myId);
		Human you = dao.searchAccount(yourId);
		boolean flagT = false;

		if (me.getInvited() == 1 && me.getSuccess() != 1) {
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
					you.setInvited(1);
					you.setSuccess(1);
					me.setSuccess(1);
					
					dao.updateLockAndMatch(me);
					dao.updateLockAndMatch(you);
					dao.updateCash(you); // 상대의 돈 차감
					
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
	 * (완료) 매칭 정보를 초기화 해주는 메소드. 다시 만남을 가질 수 있도록 해준다.
	 * 
	 * @param id
	 * @return
	 */
	public boolean initialize(String id) {
		boolean flag = false;
		Human me = searchAccount(id);
		Human you = searchAccount(me.getMatchedId());

		if (me != null && you != null) {
			me.setMatchedId("");
			me.setInvited(0);
			me.setMatchLock(0);
			me.setSuccess(0);
			you.setMatchedId("");
			you.setInvited(0);
			you.setMatchLock(0);
			you.setSuccess(0);

			dao.updateLockAndMatch(me);
			dao.updateLockAndMatch(you);

			flag = true;
		}

		return flag;
	}

	/**
	 * (완료) 회원 탈퇴를 진행하는 메소드 회원탈퇴가 성공하면 true 실패하면 false
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeAccount(String id, String pw) {
		boolean flag = false;

		Human vo = dao.searchAccount(id);
		if (pw.equals(vo.getPassword())) {
			initialize(id); // 매칭 상대가 있다면 매칭 상대도 상태를 반영해야 하므로 시행
			flag = dao.deleteAccount(vo);
		}

		return flag;
	}
}
