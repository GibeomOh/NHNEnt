package dao.board;

import java.util.List;

import model.board.BoardModel;

public class BoardDAO {
	
	
	
	// 목록 조회
    public List<BoardModel> selectList(BoardModel boardModel) {
        return null;
    }
    // 게시판 수 조회
    public int selectCount(BoardModel boardModel) {
        return 0;
    }
    
    // 글 선택
    public BoardModel select(BoardModel boardModel) {
    	
        return null;
    }
    // 글 등록
    public void insert(BoardModel boardModel) {
    	
    }
    
    // 게시판 갱신
    public void update(BoardModel boardModel) {
    	
    }
    // 조회수 증가
    public void updateHit(BoardModel boardModel) {
    	
    }
    
    // 글 삭제
    public void delete(BoardModel boardModel) {
    	
    }
}
