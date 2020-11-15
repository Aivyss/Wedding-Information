package dao;

import vo.Human;

public interface MatchAndLockMapper {
	public boolean updateLockAndMatch(Human vo);
	public boolean insertLockAndMatch(Human vo);
	public boolean updateLockCount(Human vo);
}
