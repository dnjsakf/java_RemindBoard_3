package my.remind.board3.vo;

public class CommentVO {
	
	private int no;				// auto_increment
	private String writer;
	private String date;
	private String password;
	private String comment;
	private int boardNo;		// 이걸 contentId로 했어야 했는데 이름을 잘못했네ㅠㅠ
	
	public CommentVO() {
		
	}
	public CommentVO(String writer, String date, String password, String comment, int boardNo) {
		this.writer = writer;
		this.date = date;
		this.password = password;
		this.comment = comment;
		this.boardNo = boardNo;
	}
	
	public int getNo() {
		return no;
	}
	public String getWriter() {
		return writer;
	}
	public String getDate() {
		return date;
	}
	public String getPassword() {
		return password;
	}
	public String getComment() {
		return comment;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n"+this.no);
		sb.append("/"+this.writer);
		sb.append("/"+this.date);
		sb.append("/"+this.password);
		sb.append("/"+this.comment.substring(0,3)+"..");
		sb.append("/"+this.boardNo);
		return sb.toString();
	}
	
}
