package com.sist.aop;

/*
 * aspect : AOP가 OOP대체는 아님(보완임), 공통으로 모아둔 모듈들을 aspect라 하자
 * **OOP : 콜백함수가 없다(시스템에 의해 자동호출 main, sevice같은걸로)
 * 		한, 두개 공통으로 사용되는 내용은 메소드로 처리한다. getConnection, disConnection
 * 		여러개가 공통이면 클래스로 묶음 CreateDataBase
 * 		------------------공통모듈
 * 스프링에서는 반복적인 코딩을 모아서 관리 -> 필요시에 자동 호출도 해줌 -> AOP
 * ***사용자 정의는 많이 사용하지 않는다. 대신 라이브러리를 사용함(라이브러리 : 트랜잭션, 보안, 로그파일)
 * 
 * AOP
 * ---
 * 1. 어떤메소드에 적용시킬 것인가 : Pointcut(적용할 메소드 지정)
 * -> Pointcut = "execute( * com.sist.main.*.*(..))"
 *                         --			   --- --
 *                         리턴형		    클래스명, 메소드명 (..) 매개변수x, 매개변수
 * -> Pointcut ="within("com.sist.main.*")" 
 * 	             ------패키지 단위로 등록
 *  
 * 2. 호출되는 위치설정 : JoinPoint
 * @Transectional -> 요즘이렇게 사용함 어노테이션으로 해결
 * public String display(){
 * 		@Before : getConnection()
 * 		try{
 * 			----------@Around : setAutoCommit(false) 
 * 			핵심 코드
 * 			----------@Around : Commit()
 * 		}catch{
 * 			@After-Throwing : 오류처리 : rollback()
 * 		}finally{
 * 			@After : setAutoCommit(true), disConnection()
 * 		}
 * 		retrun : @AFter-Returning
 * }
 * 
 * JoinPoint + Pointcut -> Advice -> aspect
 * Weaving : 통합 -> Proxy 패턴(대리자)
 * 
 * public Class A
 * {
 * 		public void display(){
 * 			
 * 		}
 * }
 * 
 * public Class Proxy
 * {
 * 		private A a;
 * 		public Proxy( A a) {
 * 			this.a=a;
 * 		}
 * 		public void display()
 * 		{
 * 			@Before -> 설저된 메소드 호출
 * 			a.display();
 * 			@After -> 설저된 메소드 호출
 * 		}
 * }
 * -> 이러한 상태를 위빙이라 한다.
 * 
 * 
 */
import java.util.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

@Aspect //공통사용 영역임을 명시
@Component //메모리 할당역할
public class EmpAspect {
	 
	@Autowired
	private EmpDAO dao;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))") //try 시작과 동시에 호출
	public void getConnection( ) {
		System.out.println("DB연결");
		dao.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))") //Finally에서 호출
	public void disConnection(Object ex) {
		System.out.println("DB해제");
		dao.disConnection();
	}
	
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp*(..))",returning ="ex") //정상 수행 후 return값을 받아서 처리
	public void print(Object obj) {
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getSal()+" "
					);
		}
	}
	
	@AfterThrowing(value="execution(* com.sist.dao.EmpDAO.emp*(..))",throwing ="ex") //catch절 수행시 처리
	public void cat(Throwable ex) {
		
	}
	
	
	
	

}
