package net.koreate.vo;

import java.util.Date;

public class CommentVo {
	private int cno;
	private int bno;
	private String commentText;
	private String commentAuth;
	private Date regdate;
	private Date updatedate;
	private int uno;

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentAuth() {
		return commentAuth;
	}

	public void setCommentAuth(String commentAuth) {
		this.commentAuth = commentAuth;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	@Override
	public String toString() {
		return "CommentVo { [ cno : " + this.cno
				+ " ], [ bno : " + this.bno
				+ " ], [ commentText : " + this.commentText
				+ " ], [ commentAuth : " + this.commentAuth
				+ " ], [ regdate : " + this.regdate
				+ " ], [ updatedate : " + this.updatedate
				+ " ], [ uno : " + this.uno
				+ " ] }";
	}
}
