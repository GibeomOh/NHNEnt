package servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDAO;
import model.board.BoardModel;

public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = null;
    
    public BoardListServlet() {
        super();
    }

	/**
	 * GET 접근 시 (목록 조회 접근 시)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("목록 조회");
		// 모델
		BoardModel boardModel = new BoardModel();

		// DAO
		this.boardDAO = new BoardDAO();
		// 게시물 총 수
		int totalCount = this.boardDAO.selectCount(boardModel);
		// 게시물 목록을 얻는 쿼리 실행
		List<BoardModel> boardList = this.boardDAO.selectList(boardModel);
		
		// View 사용될 객체 설정
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardModel", boardModel);
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/board/boardList.jsp");
		requestDispatcher.forward(request, response);
	}
}
