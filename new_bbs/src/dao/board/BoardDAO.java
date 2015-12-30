package dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.board.BoardModel;

public class BoardDAO {
	// 데이터베이스 관련 클래스들의 초기화
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    // 데이터베이스 접속 정보
    private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/BBS";
    private final String DB_ID = "NHNEnt";
    private final String DB_PWD = "1234";
	
	// 목록 조회
    public List<BoardModel> selectList(BoardModel boardModel) {
    	List<BoardModel> boardList = null;
    	
    	try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			this.pstmt = this.conn.prepareStatement("SELECT num, subject, email, reg_date, hit FROM board"+ " ORDER BY num DESC ");
			this.rs = this.pstmt.executeQuery();
			
			// 게시판 글 목록 저장
			boardList = new ArrayList<BoardModel>();
			while(this.rs.next()) {
				boardModel = new BoardModel();
				boardModel.setNum(this.rs.getInt("num"));
				boardModel.setSubject(this.rs.getString("subject"));
				boardModel.setEmail(this.rs.getString("email"));
				boardModel.setRegDate(this.rs.getString("reg_date"));
				boardModel.setHit(this.rs.getInt("hit"));
				boardList.add(boardModel);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
			
		}
    	
        return boardList;
    }
    
    // 글 선택
    public BoardModel select(BoardModel boardModel) {
    	try {
    		// 데이터베이스 드라이버 로딩
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		// 커넥션 정보 저장
    		this.conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    		// Query 설정
    		this.pstmt = this.conn.prepareStatement("SELECT num, subject, contents, password, email, hit, reg_date FROM board"+ " WHERE num = ?");
    		this.pstmt.setInt(1, boardModel.getNum());
    		// Query 실행
    		this.rs = this.pstmt.executeQuery();
    		
    		// Query로 얻어온 데이터를 설정
    		if(this.rs.next()) {
    			boardModel.setNum(this.rs.getInt("num"));
                boardModel.setSubject(this.rs.getString("subject"));
                boardModel.setEmail(this.rs.getString("email"));
                boardModel.setContents(this.rs.getString("contents"));
                boardModel.setPassword(this.rs.getString("password"));
                boardModel.setRegDate(this.rs.getString("reg_date"));
                boardModel.setHit(this.rs.getInt("hit"));
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		// 데이터베이스 관련 클래스들의 종료
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
    	}
    	
    	return boardModel;
    }
    
    public int selectCount(BoardModel boardModel) {
		int totalCount = 0;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			this.conn = DriverManager.getConnection(this.DB_URL, this.DB_ID, this.DB_PWD);
			this.pstmt = this.conn.prepareStatement("SELECT COUNT(num) AS total FROM board");
			this.rs = this.pstmt.executeQuery();
			
			if (this.rs.next()) {
				totalCount = this.rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
		}
		
		return totalCount;
	}
    
    // 글 등록
    public void insert(BoardModel boardModel) {
    	try {
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		this.conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    		this.pstmt = this.conn.prepareStatement("INSERT INTO board (subject, email, contents, password, writer, hit, reg_date, mod_date)"+ " VALUES (?, ?, ?, ?, ?, 0, NOW(), NOW())");
    		this.pstmt.setString(1, boardModel.getSubject());
			this.pstmt.setString(2, boardModel.getEmail());
			this.pstmt.setString(3, boardModel.getContents());
			this.pstmt.setString(4, boardModel.getPassword());
			this.pstmt.setString(5, boardModel.getWriter());
    		this.pstmt.executeUpdate();
    		System.out.println("글 등록 완료!");
    	} catch(Exception e) {
    		e.printStackTrace();
    		
    	} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
    	}
    } 
    
    // 글 수정
    public void update(BoardModel boardModel) {
    	try {
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		this.conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    		this.pstmt = this.conn.prepareStatement("UPDATE board SET subject = ?, contents = ?, mod_date = NOW()" + " WHERE num = ?");
    		this.pstmt.setString(1, boardModel.getSubject());
//			this.pstmt.setString(2, boardModel.getEmail());
			this.pstmt.setString(2, boardModel.getContents());
			System.out.println(boardModel.getContents());
			this.pstmt.setInt(3, boardModel.getNum());
    		this.pstmt.executeUpdate();
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
    	}
    }
    
    // 조회수 증가
    public void updateHit(BoardModel boardModel) {
    	try {
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		this.conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    		this.pstmt = this.conn.prepareStatement("UPDATE board SET hit = hit + 1"+ " WHERE num = ?");
    		this.pstmt.setInt(1, boardModel.getNum());
    		this.pstmt.executeUpdate();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		
    	} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
			
    	}
    }
    
    // 글 삭제
    public void delete(BoardModel boardModel) {
    	try {
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		this.conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    		this.pstmt = this.conn.prepareStatement("DELETE FROM board"+ " WHERE num = ?");
    		this.pstmt.setInt(1, boardModel.getNum());
    		this.pstmt.executeUpdate();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		
    	} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
			try { if(conn != null) conn.close(); } catch(Exception e) {}
			
    	}
    }
}
