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

@WebServlet("/board/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = null;
	private BoardModel boardModel = null;
       
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/board/boardWrite.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 한글 파라미터 깨짐 처리
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String contents = request.getParameter("contents");
		String password = request.getParameter("password");
		
		// 모델
		boardModel = new BoardModel();
		boardModel.setSubject(subject);
		boardModel.setEmail(email);
		boardModel.setContents(contents);
		boardModel.setPassword(password);
		
		// 게시물 등록
		this.boardDAO = new BoardDAO();
		this.boardDAO.insert(boardModel);

		// 페이지 이동
		response.sendRedirect("boardListServlet");
	}

}
