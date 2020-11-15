package dao;

import vo.Human;

public interface CashTableMapper {
	public int updateCash(Human vo);
	public int insertCashInfo(Human vo);
}
