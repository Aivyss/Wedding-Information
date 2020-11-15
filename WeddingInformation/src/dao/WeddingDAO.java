package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import manage.ScoreRank;
import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	private int addAccountCount = 0;
	private int updateGradeCount = 0;
	private int insertLockAndMatchCount = 0;
	private int insertCashInfoCount = 0;
	private int insertTacoCount = 0;
	private int insertSurgeryCount = 0;
	/** (완료)
	 * 회원추가
	 */	
	public boolean addAccount(Human vo) {
		SqlSession ss = null;
		boolean flag = true;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper1 = ss.getMapper(MemberMapper.class);
			MatchAndLockMapper mapper2 = ss.getMapper(MatchAndLockMapper.class);
			CashTableMapper mapper3 = ss.getMapper(CashTableMapper.class);
			MaleTableMapper mapper4 = ss.getMapper(MaleTableMapper.class);
			FemaleTableMapper mapper5 = ss.getMapper(FemaleTableMapper.class);
					
			flag = (!ScoreRank.giveScore(vo)) 										? false 
					: (mapper1.addAccount(vo) != addAccountCount+1) 				? false
					: (!ScoreRank.giveGrade(vo))									? false
					: (mapper1.updateGrade(vo) != updateGradeCount+1) 				? false
					: (mapper2.insertLockAndMatch(vo) != insertLockAndMatchCount+1) ? false 
					: (mapper3.insertCashInfo(vo) != insertCashInfoCount+1) 		? false
					: (vo instanceof Male) 											? (mapper4.insertTaco((Male) vo) == insertTacoCount+1) : (mapper5.insertSurgery((Female) vo) == insertSurgeryCount+1);
			if (flag) {
				ss.commit();
			} else {
				ss.rollback();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return flag;
	}
	
	/** (완료)
	 * gradeIndex에 따른 grade 실질값을 불러오는 메소드
	 */
	public String getGrade(int gradeIndex) {
		SqlSession ss = null;
		String output = null;
		
		try {
			ss = factory.openSession();
			GradeMapper mapper = ss.getMapper(GradeMapper.class);
			output = mapper.getGrade(gradeIndex);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return output;
	}
	
	private int updateLockAndMatchCount = 0;
	/**
	 * lock관련 정보를 업데이트하는 메소드
	 */
	public boolean updateLockAndMatch(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);

			if (mapper.updateLockAndMatch(vo) == updateLockAndMatchCount+1) {
				ss.commit();
				flag =true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return flag;
	}
	
	/** (완료)
	 * vo와 일치하는 성별의 vo객체들의 리스트를 불러오는 메소드
	 */
	public List<Human> getList(Human vo) {
		SqlSession ss = null;
		List<Human> list = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			list = mapper.getList(vo);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return list;
	}
	
	/**
	 * 전체 회원 정보를 불러 읽어오는 메소드
	 */	
	public List<Human> getAll() {
		SqlSession ss = null;
		List<Human> list = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			list = mapper.getAll();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	private int lockCountCount = 0;
	/** (완료)
	 * 락카운트를 업데이트하는 메소드
	 */
	public boolean updateLockCount(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			if(mapper.updateLockCount(vo) == lockCountCount+1) {
				ss.commit();
				flag = true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return flag;
	}
	
	/** (완료)
	 * 회원찾기
	 */
	public Human searchAccount(String id) {
		SqlSession ss = null;
		Human vo = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			vo = mapper.searchAccount(id);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss!=null) ss.close();
		}
		
		return vo;
	}
	
	private int updateCashCount = 0;
	/** (완료)
	 * 충전금의 변동을 업데이트하는 메소드
	 */
	public boolean updateCash(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			CashTableMapper mapper = ss.getMapper(CashTableMapper.class);
			
			if(mapper.updateCash(vo) == updateCashCount+1) {
				ss.commit();
				flag = true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if(ss!=null) ss.close();
		}
		
		return flag;
	}
	
	private int deleteAccountCount = 0;
	/**(완료)
	 * 회원탈퇴를 진행하는 메소드
	 * @param vo
	 * @return
	 */
	public boolean deleteAccount(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			
			if(mapper.deleteAccount(vo) == deleteAccountCount+1) {
				ss.commit();
				flag = true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss!=null) ss.close();
		}
		
		return flag;
	}
}
