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
	
	/**
	 * id�� ȸ���� ã�Ƽ� vo��ü�� ��ȯ�ϴ� �޼ҵ�
	 * @param id
	 * @return ȸ���� ã�� ���ҽÿ��� null�� ��ȯ��
	 */
	public Human searchAccount(String id) {
		return humanMap.get(id);
	}
	
	/**
	 * ����� �ο��ϴ� �޼ҵ�
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
				rank[i].setLevel("��");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.7) {
				rank[i].setLevel("�����");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.56) {
				rank[i].setLevel("�ǹ�");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.42) {
				rank[i].setLevel("���");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.28) {
				rank[i].setLevel("�÷�Ƽ��");
			} else if ((i*1.0)/(numOfPeople*1.0) > 0.14) {
				rank[i].setLevel("���̾�");
			} else {
				rank[i].setLevel("����");
			}
		}
	}
	
	/**
	 * ����ȸ���� ������ �ű�� ���μ���
	 * @param voF
	 */
	public void giveFemaleScore(Female voF) {
		// ���̿� ���� �����ο�
		if (voF.getAge() >= 35) {
			voF.setAgeScore(72);
		} else if (voF.getAge() >=30) {
			voF.setAgeScore(76);
		} else if (voF.getAge() >=25) {
			voF.setAgeScore(78);
		} else {
			voF.setAgeScore(80);
		}
		
		// ������ ���� ���� ó��
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
