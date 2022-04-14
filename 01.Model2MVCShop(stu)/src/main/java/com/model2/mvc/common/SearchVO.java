package com.model2.mvc.common;


public class SearchVO {
	
	//F
	private int page;	// ���� ������
	String searchCondition;
	String searchKeyword;
	int pageUnit;	// �������� ��Ͽ� ��µǴ� �Խù� ���� 
	
	//C
	public SearchVO(){
	}
	
	//M
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	// toString() override
	@Override
	public String toString() {
		return "SearchVO [page=" + page + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageUnit=" + pageUnit + "]";
	}	
	
}