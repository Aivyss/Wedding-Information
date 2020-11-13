package dao;

import vo.Human;

public interface MatchAndLockMapper {
	public int initializeLockInfo(Human vo);
	public void updateLockCount(Human vo);
}
