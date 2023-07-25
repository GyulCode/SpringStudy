package com.sist.main;
/*
 * 이미 제작되어 있는 라이브러리
 *  선언방법
 *  1.xml
	2.annotation
	3.xml + annotation(이게 추세)
 * ---------------------
 * 1. Container : 사용자가 등록한 클래스를 관리 (POJO 방식 : 일반 클래스, 인터페이스, 상속이 없는 )
 * 2. DI : 클래스와 클래스간의 의존관계 설정 / 변수의 초기화, 필요한 메소드 호출
 * 2-1. XML로 등록(메뉴얼)
 * -> XML, Annotation -> Container -> 프로그램 동작이 가능
 * -> 동작(XML을 제공)
 * -> Container
 * 		1. BeanFactory
 * 		2. ApplicationContext(XML)
 * 			순수하게 자바(보안)로 구성 이거는 spring5버전(보안) 에서 적용
 * 			AnnotationConfigApplicationContext
 * 		3.WebApplicationContext
 * -> 빈생성자 (클래스 메모리 할당)
 * <bean id="" Class=""> -> Class.forName("A") -> 찾는 조건이 ID, ID는 중복이 불가능 하다
 * 의존성 주입(DI) : class가 동작하기 위한 멤버변수(일반변수[기본형int, double, string...], 클래스 객체(주소)) 초기화
 *    ***일반 멤버변수느 데이터베이스 정보되에는 거의 사용 빈도가 없다.
 * p : name -> property 일반변수 설정
 * p : dao-ref -> 생성된 객체의 주소값 설정
 * init-method : 생성과 동시에 자동으로 메소드 호출, 크롤링, 트위터 읽기, 자동 로그인, 드라이버 등록
 * 개발자는 등록된 클래스 객체를 활용
 * destroy-method : 객체 소멸시 호출 db.close()....
 * DI(스프링을 통해서 프로그램 동작을 위해 필요한 데이터르 전송)
 * 
 * Data와 DAO연결 <- 이걸 스프링이 DAO 객체의 주소를 받아서 DAO관리를 한다.
 * 클래스에 설정된 변수에 대한 값을 주입
 * 1. 명시적 초기화(x)
 * 2. 생성자 매개변수에 값을 지정
 * 3. setter(property) / setXXX()에 값을 지정
 * c: 매개변수를 이용해서 값을 넣는다
 * 클래스가 많아지면 xml이 커진다(속도가 늦어짐)
 * -> 패키지 단위로 등록 -> 스프링에서 등록 구분은 어노테이션으로 한다.
 * 
 * 1. 사용자 정의 클래스
 * 2. 라이브러리 클래스 (<BEAN>)
 * -------------------------
 * XML : 파일한개로 공통으로 사용한다(공동 개발시 사용)
 * Annotation : 클래스 마다 따로 개발(개발자 마다 개인)
 * ->*** 오늘 할거 MyBatis와 연결하고 annotation사용
 * MyBatis역시 XML, Annotation, Xml+Annotation 3가지 방법으로 선언가능
 * 
 * 
 * 
 * 
 *       
 * 3. AOP :  공통 모듈을 모아서 적용(중복 제거)
 * 4. MVC : 웹 관련(데이터 관리 + 화면출력)
 * 5. Front + Back : Restful(파일전송 방식 json사용)
 * 6. 보안(권한, 암호화, 복호화)
 * ----------------------
 * 기타 : 데이터베이스 (ORM) -> MyBatis, 트랜잭션
 * 
 * 
 */

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {
	
	public static void main(String[] args) {
		//spring 에 등록
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "
					+ vo.getEname()+" "
					+ vo.getJob()+" "
					+ vo.getDeptno()+" "
					);
		}
		System.out.println("==============");
		Scanner sc=new Scanner(System.in);
		System.out.println("사번 입력 : ");
		int empno=sc.nextInt();
		EmpVO vo=dao.empDetailData(empno);
		System.out.println("======= 사원 정보 =======");
		System.out.println("사번 : "+vo.getEmpno());
		System.out.println("이름 : "+vo.getEname());
		System.out.println("직위 : "+vo.getJob());
		System.out.println("사수명 : "+vo.getMgr());
		System.out.println("입사일1 : "+vo.getHiredate());
		System.out.println("입사일 : "+vo.getDbday()); //vo.hiredate()
		System.out.println("급여 : "+vo.getSal());
		System.out.println("성과급 : "+vo.getComm());
		System.out.println("부서 : "+vo.getDeptno());
	}
	
			

}
