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

@WebServlet("/board/BoardModifyServlet")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = null;
    
    public BoardModifyServlet() {
        super();
    }

	/** 수정 화면 얻어오기 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 얻어 오기
		String num = request.getParameter("num");
		
		// 모델 설정
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));

		// 게시물 상세 조회
		this.boardDAO = new BoardDAO();
		boardModel = this.boardDAO.select(boardModel);
		
		// View 사용될 객체 설정
		request.setAttribute("boardModel", boardModel);
		
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/board/boardModify.jsp");
		requestDispatcher.forward(request, response);
	}

	/** 수정된 정보 보내기 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터 얻어 오기
		String num = request.getParameter("num");
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String contents = request.getParameter("contents");
		
		// 모델 설정
		BoardModel boardModel = new BoardModel();
		boardModel.setNum(Integer.parseInt(num));
		boardModel.setSubject(subject);
		boardModel.setWriter(writer);
		boardModel.setEmail(email);
		boardModel.setContents(contents);

		// 게시물 수정 처리
		this.boardDAO = new BoardDAO();
		this.boardDAO.update(boardModel);
		
		// 페이지 이동	
		response.sendRedirect("BoardViewServlet?num=" + num);
	}
}
