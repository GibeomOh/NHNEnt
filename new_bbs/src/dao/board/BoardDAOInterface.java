package dao.board;

import java.util.List;

import model.board.BoardModel;

public interface BoardDAOInterface {
	/** 게시판 목록 조회 */
	public List<BoardModel> selectList(BoardModel boardModel);
	
	/** 게시판 수 조회 */
	public int selectCount(BoardModel boardModel);
	
	/** 글 상세 조회 */
	public BoardModel select(BoardModel boardModel);
	
	/** 글 등록 */
	public void insert(BoardModel boardModel);
	
	/** 글 수정 */
	public void update(BoardModel boardModel);
	
	/** 글 조회수 증가 */
	public void updateHit(BoardModel boardModel);
	
	/** 글 삭제 */
	public void delete(BoardModel boardModel);
}
