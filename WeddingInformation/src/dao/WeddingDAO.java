package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingDAO implements MemberMapper, MatchAndLockMapper, GradeMapper, MaleTableMapper, FemaleTableMapper, CashTableMapper {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	@Override
	public int initializeLockInfo(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			mapper.initializeLockInfo(vo);
			count++;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			ss.commit();
			if (ss != null) ss.close();
		}
		
		return count;
	}

	@Override
	public int addAccount(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			mapper.addAccount(vo);
			count++;
			ss.commit();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return count;
	}

	@Override
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

	@Override
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

	@Override
	public int updateGrade(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			mapper.updateGrade(vo);
			count++;
			ss.commit();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
		
		return count;
	}
	
	/**
	 * 전체 회원 정보를 불러 읽어오는 메소드
	 */
	@Override
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

	@Override
	public void updateLockCount(Human vo) {
		SqlSession ss = null;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			mapper.updateLockCount(vo);
			ss.commit();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss != null) ss.close();
		}
	}
	
	/**
	 * 남성의 탈모여부를 남성테이블에 기록한다.
	 */
	@Override
	public int insertTaco (Male vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			MaleTableMapper mapper = ss.getMapper(MaleTableMapper.class);
			mapper.insertTaco(vo);
			ss.commit();
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return count;
	}

	@Override
	public int insertSurgery(Female vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss=factory.openSession();
			FemaleTableMapper mapper = ss.getMapper(FemaleTableMapper.class);
			mapper.insertSurgery(vo);
			ss.commit();
			count++;
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return count;
	}
	
	@Override
	public int deposit(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			CashTableMapper mapper = ss.getMapper(CashTableMapper.class);
			mapper.deposit(vo);
			ss.commit();
			count++;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss!=null) ss.close();
		}
		
		return count;
	}

	@Override
	public int initializeCash(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			CashTableMapper mapper = ss.getMapper(CashTableMapper.class);
			mapper.initializeCash(vo);
			ss.commit();
			count++;
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (ss!=null) ss.close();
		}
		
		return count;
	}
	
	// 회원정보를 저장하는 메소드
}
