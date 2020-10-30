package manage;

import java.util.HashMap;
import java.util.Map;

import vo.Human;

public class HumanInfo {
	public Map<String, Human> humanMap; // <id, vo>
	
	/**
	 * »ý¼ºÀÚ
	 */
	public HumanInfo() {
		humanMap = new HashMap<>();
	}
	
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
