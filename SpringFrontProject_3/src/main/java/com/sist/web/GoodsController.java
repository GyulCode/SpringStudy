package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 자바
 * 	= 기본자료형(데이터형), 변수, 연산자, 제어문, 배열(1차)
 * 	= 객체지향 프로그램(클래스/객체) 객체지향 3대 요소 (캡슐화,상속,포함,다형성)
 * 		** 캡슐화 VS 은닉화
 * 		** 상속 VS 포함
 * 		** 오버라이딩 VS 오버로딩
 *  = 추상클래스/인터페이스
 * 		** 추상클래스 VS 인터페이스
 * 		** 인터페이스의 장점 및 왜 사용하는가
 *  = 예외처리
 * 		** 예외처리의 목적 : 사전에 에러 방지, 프로그램이 종료되지 않게 하기 위해
 *  = 라이브러리 (java.lang, java.util)
 *  	= Collection, 제네릭스
 *  	  ------------------개념정리
 *  	= IO
 * 오라클
 * 	= DQL(SELECT) -> JOIN/SubQuery
 * 	  *** JOIN의 종류
 * 	  *** SubQeury의 종류
 * 	= DML(INSERT, UPDATE, DELETE)
 * 	= DDL(CREATE, ALTER, DROP, TRUNCATE) -> 제약조건
 * 	  *** 제약조건 종류
 * 	= TCL(COMMIT, ROLLBACK)
 *  = PL/SQL(프로시저)
 * 	  *** 프로시저와 트리거 차이점
 *  = JDBC (DBCP/ORM(MyBatis,JPA)
 * 	  *** DAO와 service의 차이점
 * HTML5/CSS(수정 정도)
 * 	  *** GET / POST의 차이
 * JavaScript (기본) -> 변수(let, const), 제어문/연산자
 * 					-> 함수
 * 					-> 이벤트
 * 					-> 태그 제어(DOM)
 * 					-> 라이브러리 (Jqeury,VueJS,React)
 * 	  *** var / let / const
 * 	  *** 클로저
 * 	  *** prototype
 * 	  *** VueJS, React의 차이
 * JSP : 지시자(page,taglib), 내장객체(request,response,session,application), 
 * 		액션태그(<jsp:include>,<jsp:)
 * 		TagLib(<c:~~>), EL(${})
 * 		MVC
 * 			*** MVC구조 및 동작 설명
 * 
 * Spring : DI, AOP, MVC - 기본 
 * 		옵션추가공부 : websocket, task, security, spring-boot 
 * 		*** DI가 뭔지
 * 		*** AOP가 뭔지
 * 
 * AWS : 호스팅
 * ---------------------정리 시작~
 * 
 */
@Controller
public class GoodsController {
	@GetMapping("goods/list.do")
	public String goods_list() {
		return "good/list";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		model.addAttribute("no",no);
		return "goods/detail";
	}
	
	

}
