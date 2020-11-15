package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	int updateLockAndMatchCount = 0;
	/** (완료)
	 * 잠금 정보를 초기화하는 메소드
	 */
	public boolean updateLockAndMatch(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			
			if(mapper.updateLockAndMatch(vo) == updateLockAndMatchCount+1) {
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
	
	int addAccountCount = 0;
	/** (완료)
	 * 회원추가
	 */	
	public boolean addAccount(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			
			if (mapper.addAccount(vo) == addAccountCount+1) {
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
	
	int updateGradeCount = 0;
	/** (완료)
	 * 등급을 업데이트하는 메소드
	 */	
	public boolean updateGrade(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			if(mapper.updateGrade(vo) == updateGradeCount+1) {
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
	
	int lockCountCount = 0;
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
	
	int tacoCount = 0;
	/** (완료)
	 * 남성의 탈모여부를 남성테이블에 기록한다.
	 */
	public boolean insertTaco (Male vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MaleTableMapper mapper = ss.getMapper(MaleTableMapper.class);
			if(mapper.insertTaco(vo) == tacoCount+1) {
				ss.commit();
				flag = true;
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return flag;
	}
	
	
	int insertSurgeryCount = 0;
	/** (완료)
	 * 여성고객의 성형정보를 저장하는 메소드
	 */
	public boolean insertSurgery(Female vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss=factory.openSession();
			FemaleTableMapper mapper = ss.getMapper(FemaleTableMapper.class);

			if (mapper.insertSurgery(vo) == insertSurgeryCount+1) {
				ss.commit();
				flag = true;
			}
		} catch (Exception e) {
			e.getStackTrace();
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
	
	int updateCashCount = 0;
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
	
	int insertLAMCount = 0;
	/** (완료)
	 * 초기 잠금 정보를 입력하는 메소드 
	 */
	public boolean insertLockAndMatch(Human vo) {
		SqlSession ss = null;
		boolean flag = false;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			
			if(mapper.insertLockAndMatch(vo) == insertLAMCount+1) {
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
