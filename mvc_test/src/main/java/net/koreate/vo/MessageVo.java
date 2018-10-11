package net.koreate.vo;

import java.util.Date;

public class MessageVo {
	int mno;
	String targetid;
	String sender;
	String message;
	Date opendate;
	Date senddate;

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	@Override
	public String toString() {
		return "MessageVo { [ mno : " + this.mno
				+ " ], [ targetid : " + this.targetid
				+ " ], [ sender : " + this.sender
				+ " ], [ message : " + this.message
				+ " ], [ opendate : " + this.opendate
				+ " ], [ senddate : " + this.senddate
				+ " ] }";
	}
}
