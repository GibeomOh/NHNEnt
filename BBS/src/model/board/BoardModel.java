package model.board;

public class BoardModel {
    private int num;			// 글 번호
    private String subject;		// 제목
    private String writer;		// 작성자
    private String password;	// 비밀번호
    private String email;		// 이메일
    private String contents;	// 내용
    private int hit;				// 조회수
    private String regDate;		// 등록 일시
    private String modDate;	// 수정 일시
    private String pageNum;
    /** 검색 항목 */
    private String searchType;
    /** 검색어 */
    private String searchText;
    
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
