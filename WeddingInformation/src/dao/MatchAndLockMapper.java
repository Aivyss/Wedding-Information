package dao;

import vo.Human;

public interface MatchAndLockMapper {
	public int insertLockInfo(Human vo);
}
