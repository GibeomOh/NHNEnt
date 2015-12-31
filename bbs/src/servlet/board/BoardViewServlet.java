package servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDAO;
import model.board.BoardModel;

@WebServlet("/board/BoardViewServlet")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = null;
	
    public BoardViewServlet() {
        super();
    }

	/** 상세 조회 접근 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상세 조회 하는 글 번호
		String num = request.getParameter("num");

		// 모델 정보 입력
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));
		
		// 게시물 상세 조회
		this.boardDAO = new BoardDAO();
		boardModel = this.boardDAO.select(boardModel);
		
		// 게시물 조회수 증가
		this.boardDAO.updateHit(boardModel);
		
		// View 사용될 객체 설정
		request.setAttribute("boardModel", boardModel);
		
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/board/boardView.jsp");
		requestDispatcher.forward(request, response);
	}
}
