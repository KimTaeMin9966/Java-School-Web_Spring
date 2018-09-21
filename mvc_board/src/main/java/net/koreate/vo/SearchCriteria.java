package net.koreate.vo;

public class SearchCriteria extends Criteria {

	String searchType;
	String Keyword;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchCriteria { [ searchType : " + this.searchType
				+ " ], [ Keyword : " + this.Keyword
				+ " ] }";
	}
}
