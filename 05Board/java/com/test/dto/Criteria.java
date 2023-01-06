package com.test.dto;

public class Criteria {
	private int pageno;		// 현재페이지
	private int amount;		// 표시할 게시물 양(10건정도)
	private String type;	// 제목,작성자,게시물번호 검색
	private String keyword;	// 포함문자열
	
	//default 생성자
	public Criteria() {
		pageno = 1;
		amount = 10;
	}
	
	public Criteria(int no, int amt)
	{
		pageno = no;
		amount = amt;
	}
	
	//getter setter
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	//toString()
	@Override
	public String toString() {
		return "Criteria [pageno=" + pageno + ", amount=" + amount + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
	
}
