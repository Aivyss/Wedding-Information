package dao;

import java.util.List;

import vo.Human;

public interface MemberMapper {
	public int addAccount(Human vo);
	public List<Human> getList(Human vo);
	public int updateGrade(Human vo);
	public List<Human> getAll();
}
