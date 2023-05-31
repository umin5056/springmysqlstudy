package com.gdu.app09.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class PageUtil {

	private int page;				// 현재 페이지(파라미터로 받아온다)
	private int totalRecord;		// 전체 레코드 수(DB에서 구해온다	)
	private int recordPerPage;		// 페이지당 레코드 수(파라미터로 받아온다)
	private int begin;				// 한 페이지에 표시할 레코드의 시작 번호 (계산한다.)
	
	private int pagePerBlock = 5; 	// 한 블록 당 표시할 페이지 수(임의로 지정)
	private int totalPage;			// 전체 페이지 개수(계산한다.)
	private int beginPage;			// 한 블록 당 표시할 페이지의 시작 번호
	private int endPage;			// 한 블록 당 표시할 페이지의 종료 번호
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {

		// page, totlaRecord, recordPerPage 저장
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		
		// begin 계산
		begin = (page-1) * recordPerPage;
		
		// totalPage 계산
		totalPage = totalRecord/ recordPerPage;
		if(totalRecord % recordPerPage > 0) {
			totalPage++;
		}
		
		// beginPage, endPage 계산
		/*
		   	totalPage=6, pagePerBlock=4인 상황
		   	block	beginPage	endPage
		   	1		1			4
		   	2		5			6
		 */
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		
	}
	
	public String getPagination(String path) {
		
		// path에 ?가 포함되어 있으면 이미 파라미터가 포함된 경로이므로 &를 붙여서 page 파라미터를 추가한다.
		if(path.contains("?")) {
			path += "&"; 
		}else {
			path += "?";
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"pagination\">");
		
		// 이전 블록 : 1블록은 이전 블록이 없고, 나머지 블록은 이전 블록이 있다.
		if(beginPage == 1) {
			sb.append("<span class=\"hidden\"> 이전 </span>");
			
		}else {
			sb.append("<a class=\"link\" href=\"" + path + "page=" + (beginPage - 1) + "\"> 이전 </a>");
		}
	
		// 페이지 번호 : 현재 페이지는  링크가 없다.	
		for(int p = beginPage; p <= endPage; p++) {
			if(p==page) {
				sb.append("<span class=\"strong\">"+ p + "</span>");
			}else {
				sb.append("<a class=\"link\" href=\"" + path + "page=" + p + "\">" + p + "</a>");
				
			}
		}
		
		// 다음 블록 : 마지막 블록은 다음 블록이 없고, 나머지 블록은 다음 블록이 있다.
		if(endPage == totalPage) {
			sb.append("<span class=\"hidden\"> 다음 </span>");
			
		}else {
			sb.append("<a class=\"link\" href=\"" + path + "page=" + (endPage + 1) + "\"> 다음 </a>");
			
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
}
