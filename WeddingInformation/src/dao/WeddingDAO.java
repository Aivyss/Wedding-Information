package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Human;

public class WeddingDAO implements HumanMapper, MatchAndLockMapper, GradeMapper {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	@Override
	public int insertLockInfo(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			MatchAndLockMapper mapper = ss.getMapper(MatchAndLockMapper.class);
			mapper.insertLockInfo(vo);
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
	public int insertHumanTuple(Human vo) {
		SqlSession ss = null;
		int count = 0;
		
		try {
			ss = factory.openSession();
			HumanMapper mapper = ss.getMapper(HumanMapper.class);
			mapper.insertHumanTuple(vo);
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
		
		return null;
	}
	
	// 회원정보를 저장하는 메소드
}
