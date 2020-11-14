package dao;

import vo.Human;

public interface CashTableMapper {
	public int deposit(Human vo);
	public int initializeCash(Human vo);
}
