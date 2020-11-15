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
			
		
			flag = (!ScoreRank.giveScore(vo)) 			? false 
					: (!mapper1.addAccount(vo)) 		? false
					: (!ScoreRank.giveGrade(vo))		? false
					: (!mapper2.insertLockAndMatch(vo)) ? false 
					: (!mapper3.insertCashInfo(vo)) 	? false
					: (vo instanceof Male) 				? (mapper4.insertTaco((Male) vo)) : (mapper5.insertSurgery((Female) vo));
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
	
	/**
	 * lock관련 정보를 업데이트하는 메소드
	 */
	public boolean updateLockAndMatch(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);

			if (mapper.updateLockAndMatch(vo)) {
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
	
	/** (완료)
	 * 락카운트를 업데이트하는 메소드
	 */
	public boolean updateLockCount(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			if(mapper.updateLockCount(vo)) {
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
	
	/** (완료)
	 * 충전금의 변동을 업데이트하는 메소드
	 */
	public boolean updateCash(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			CashTableMapper mapper = ss.getMapper(CashTableMapper.class);
			
			if(mapper.updateCash(vo)) {
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
	
	/**
	 * 회원의 등급을 수정하는 메소드
	 */
	public boolean updateGrade(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			
			if(mapper.updateGrade(vo)) {
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
			
			if(mapper.deleteAccount(vo)) {
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
