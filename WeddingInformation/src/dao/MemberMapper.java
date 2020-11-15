package dao;

import java.util.List;

import vo.Human;

public interface MemberMapper {
	/**
	 * 계정생성
	 */
	public int addAccount(Human vo);
	
	/**
	 * 계정 검색
	 */
	public Human searchAccount(String id);
	
	/**
	 * vo와 같은 성별의 고객 리스트 출력
	 */
	public List<Human> getList(Human vo);
	
	/**
	 * 모든 고객 리스트 출력
	 */
	public List<Human> getAll();
	
	/**
	 * 고객의 등급 수정
	 */
	public int updateGrade(Human vo);
	
	/**
	 * 회원탈퇴
	 */
	public int deleteAccount(Human vo);
}
