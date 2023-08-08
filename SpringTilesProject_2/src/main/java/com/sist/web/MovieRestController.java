package com.sist.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //response body�� ����
public class MovieRestController {
	
	//searchMainDailyBoxOffice.do
	//searchMainRealTicket.do
	//searchMainDailySeatTicket.do
	
	private String[] urls= {"","https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do",
			"https://www.kobis.or.kr/kobis/business/main/searchMainRealTicket.do",
			"https://www.kobis.or.kr/kobis/business/main/searchMainDailySeatTicket.do"};
	
	@GetMapping(value="movie/movie_rank_vue.do",produces="text/plain;charsetUTF-8")
	/*
	 *	1.html -> text/html
	 *	2.xml -> text/xml
	 *	3.json -> text/plain
	 *
	 *	//JS�� �迭, ��ü�� �ν� ���ؼ� �Ʒ��� ���� �������� �ٲ���
	 * 	vo -> {} -> JSON OBJECT (�ڹٿ����� �̷��Ժθ�)
	 * 	list[{},{},{}] -> JSONArray
	 *  Rest -> React / Redux / Vue / AngularJS
	 *  
	 */
	public String movie_rank(int no) {
		return "";
	}
	
	
	
	
}
