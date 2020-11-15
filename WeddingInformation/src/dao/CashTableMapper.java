package dao;

import vo.Human;

public interface CashTableMapper {
	public boolean updateCash(Human vo);
	public boolean insertCashInfo(Human vo);
}
