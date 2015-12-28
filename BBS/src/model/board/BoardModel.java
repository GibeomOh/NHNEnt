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
    public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
}
