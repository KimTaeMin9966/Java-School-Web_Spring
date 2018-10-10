package net.koreate.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	int totalCount; // 전체 데이터 개수
	int startPage; // 시작 페이지
	int endPage; // 마지막 페이지
	boolean prev; // 이전페이지 존재 여부
	boolean next; // 다음페이지 존재 여부
	int displayPageNum = 10; // 한번에 보여줄 페이지 개수
	Criteria cri; // 페이지 정보

	public void calData() {
		endPage = (int) Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum;
		startPage = (endPage - displayPageNum) + 1;

		int temp = (int) Math.ceil(totalCount / (double) cri.getPerPageNum());

		if (endPage > temp) { endPage = temp; }
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public String makeQuery(int page) {
		UriComponents uc = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uc.toString();
	}
	
	public String makeSearch(int page) {
		UriComponents uc = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uc.toString();
	}
	
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0 ) return "";
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) { e.printStackTrace(); return ""; }
	}
	
	@Override
	public String toString() {
		return "PageMaker { [ totalCount : " + this.getTotalCount()
				+ " ], [ startPage : " + this.getStartPage()
				+ " ], [ endPage : " + this.getEndPage()
				+ " ], [ displayPageNum : " + this.getDisplayPageNum()
				+ " ], [ cri : " + this.getCri()
				+ " ] }";
	}
}
