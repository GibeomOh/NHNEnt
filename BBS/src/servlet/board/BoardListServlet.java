package servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDAO;
import model.board.BoardModel;

@WebServlet("/board/BoardListServlet")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO boardDAO = null;
	private BoardModel boardModel = null;
       
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.boardDAO = new BoardDAO();
		this.boardModel = new BoardModel();
		
		// 게시물 총 수
		int totalCount = this.boardDAO.selectCount(boardModel);
		// 게시물 목록을 얻는 쿼리 실행
		List<BoardModel> boardList = this.boardDAO.selectList(boardModel);

		// View 사용될 객체 설정
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardModel", boardModel);
				
		// View 보내기
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsps/board/boardList.jsp");
		requestDispatcher.forward(request, response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
