package manage;

import java.util.HashMap;
import java.util.Map;

import vo.Human;

public class HumanInfo {
	public Map<String, Human> humanMap; // <id, vo>
	
	/**
	 * ������
	 */
	public HumanInfo() {
		humanMap = new HashMap<>();
	}
	
	/**
	 * �α����� �����ϴ� �޼ҵ��̴�.
	 * @param id
	 * @param pw
	 * @return �α����� �Ǹ� ��ü��ȯ �����ϸ� null ��ȯ
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
	 * ���ο� �������� ������ ȸ����Ͽ� �߰��ϴ� �޼ҵ�
	 * @param vo
	 * @return ������ ���������� ó���Ǹ� true�� ��ȯ
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
