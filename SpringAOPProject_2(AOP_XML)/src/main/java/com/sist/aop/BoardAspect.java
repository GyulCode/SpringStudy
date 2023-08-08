package com.sist.aop;
/*
 * 언제 어디서 호출할지 여부 확인
 * 1. 시점 -> pointcut -> 메소드실행시 여기서 실행해라~
 * 2. 호출위치 -> Joinpoint
 * @Before : try 시작전에 호출
 * @After : finally
 * @Around : 위 아래 -> 로그파일(시작, 끝, 시간 체크), 트랜잭션 처리
 * @After - Throwing : catch(오류발생)
 * @After-Returning : 정상수행후 
 * 
 * 
 * @Transactional -> 이 어노테이션이 들어가야하 AOP설정이 된다. 
 * public void display(){
 * 	try{
 * 			@Before
 * 			@Around -> conn.setAutoCommit(false)
 * 
 * 			--핵심 소스코드--
 * 						---
 * 
 * 	} catch(Exception ex){
 * 			@After-Throwing conn.rollback()
 * 	} finally {
 * 			@After conn.setAutoCommit();
 * 	}
 * 		return ""; @After-Returning
 * 
 * }
 * ----------------------advice
 * 
 * ----------------------aspect
 * 
 */
public class BoardAspect {
	public void before() { //문장수행전에 호출
		System.out.println("오라클 연결");
	}
	
	public void after() { //수행이 종료 -> finally
		System.out.println("오라클 해제");
		
	}

}
