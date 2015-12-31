package dao.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import model.board.BoardModel;

public class BoardMyBatisDAO implements BoardDAOInterface {
	// SQL을 실행할 때 사용할 도구를 만들어주는 SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory = MyBatisConfig.getSqlSession();

	/** 게시판 목록 조회 */
	public List<BoardModel> selectList(BoardModel boardModel) {
		// SQL을 실행하는 도구 SqlSession
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try {
			// 게시판 글을 얻고자 SqlSession의 selectList() 호출
			return sqlSession.selectList("dao.board.BoardDAO.selectList", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return null;
	}

	/** 게시판 글 수 조회 */
	public int selectCount(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try {
			return sqlSession.selectOne("dao.board.BoardDAO.selectCount", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return 0;
	}

	/** 글 상세 조회 */
	public BoardModel select(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("dao.board.BoardDAO.select", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
		return null;
	}

	/** 글 등록 */
	public void insert(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try {
			sqlSession.insert("dao.board.BoardDAO.insert", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	/** 글 수정 */
	public void update(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try {
			sqlSession.update("dao.board.BoardDAO.update", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	/** 글 조회수 증가 */
	public void updateHit(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try {
			sqlSession.insert("dao.board.BoardDAO.updateHit", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	/** 글 삭제 */
	public void delete(BoardModel boardModel) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		try {
			sqlSession.delete("dao.board.BoardDAO.delete", boardModel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}
}
