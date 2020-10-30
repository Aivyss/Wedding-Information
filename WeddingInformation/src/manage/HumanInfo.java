package manage;

import java.util.HashMap;
import java.util.Map;

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
	
	public Human match(Human vo) {
		Human another = null;
		
		return another;
	}
}
