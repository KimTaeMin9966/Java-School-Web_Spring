package net.koreate.vo;

import java.util.Date;

public class replyBoardVo {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int origin;
	private int depth;
	private int seq;
	private Date regdate;
	private Date updatedate;
	private int viewcnt;

	public int getOrigin() { return origin; }
	public void setOrigin(int origin) { this.origin = origin; }
	public int getDepth() { return depth; }
	public void setDepth(int depth) { this.depth = depth; }
	public int getSeq() { return seq; }
	public void setSeq(int seq) { this.seq = seq; }
	public Date getUpdatedate() { return updatedate; }
	public void setUpdatedate(Date updatedate) { this.updatedate = updatedate; }
	public int getBno() { return bno; }
	public void setBno(int bno) { this.bno = bno; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	public String getWriter() { return writer; }
	public void setWriter(String writer) { this.writer = writer; }
	public Date getRegdate() { return regdate; }
	public void setRegdate(Date regdate) { this.regdate = regdate; }
	public int getViewcnt() { return viewcnt; }
	public void setViewcnt(int viewcnt) { this.viewcnt = viewcnt; }

	@Override
	public String toString() {
		return "replyBoardVo { [ bno : " + this.bno
				+ " ], [ title : " + this.title
				+ " ], [ content : " + this.content
				+ " ], [ writer : " + this.writer
				+ " ], [ origin : " + this.origin
				+ " ], [ depth : " + this.depth
				+ " ], [ seq : " + this.seq
				+ " ], [ regdate : " + this.regdate
				+ " ], [ updatedate : " + this.updatedate
				+ " ], [ viewcnt : " + this.viewcnt
				+ " ] }";
	}
}
