package dao;

import vo.Human;

public interface MatchAndLockMapper {
	public int updateLockAndMatch(Human vo);
	public int insertLockAndMatch(Human vo);
	public int updateLockCount(Human vo);
}
