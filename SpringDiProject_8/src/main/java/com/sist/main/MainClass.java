package com.sist.main;
/*
 1. 프레임워크
 	라이브러리 : 완제품
 	프레임워크 : 레고에 비유
 		스프링 (전자정부 프레임워크(공기업), 애니프레임워크(대기업))
 		개발에서 기본이 되는 틀을 제공(기능을 추가해서 사용)
 		스프링의 목표 (로드 존스) -> 오픈소스
 			1. 클래스와 클래스간의 느슨한 결합=낮은 결합도 유지 / 변경시 다른 클래스에 영향이 없도록(POJO)
 			2. 메소드마다 반복하는 소스코드는 공통 모듈로 분리(응집력이 높게 제작) / AOP : OOP를 보완
 		기능
 			1. 클래스를 모아서 관리(생성~소멸) -> *컨테이너* / 생성에 필요한 데이터(값, 객체(주소))를 넣어준다 -> DI
 			DI : 객체 생성, 클래스의 연관관계 
 			AOP : 공통모듈(AOP기반 -> 로그파일, 트랜잭션, 보안)
 			위의 두방법다 인터페이스 기반이다.
 		
 		Spring 입문으로 공부해야할거
	 		1. 컨테이너의 종류
	 			BeanFactory : 가장 단순한 컨테이너로 사용자가 만든 클래스만 관리한다->DI //CORE
	 			ApplicationContext : AOP, 메세지 지원(JMS)
	 			ClassPathApplication
	 			FileSystemXmlApplication
	 			WebApplicationContext : MVC(웹)
	 			
	 			Container의 역할
	 			1) DL DataLookup : 클래스를 찾아주는 역할 getBean() //app.xml에 선언된거?
	 			2) DI DataInjection : 필요한 데이터를 받아서 초기화(변수)
	 				= setter Di(세터)
	 				= constructor(생성자) 생성자의 매개변수 이용
	 			
	 		2. DI (값을 스프링에 전송하는 방법) // 생성자, setter, xml가 아마 대표적임
	 			변수에 값 주입, 객체에 주소 할당 등이 여기에 해당된다.
	 			변수가 private이라 접근이 어려움 그래서 setter, 생성자 사용한거임
	 			p:변수명(setXX()), c:변수명(매개변수명)
	 			
	 			+++ 스프링 지원 라이브러리
	 			1) Spring Core : DI(객체 생성, 소멸) -> Container
	 			2) Spring AOP : 공통모듈 -> 자동호출
	 				a)Before
	 				b)After
	 				c)After-Returning
	 				d)After-Throwing
	 				e)Around
	 				f)JoinPoint, PointCut, 위빙
	 				g)Advice
	 			3) Spring ORM : 데이터베이스 연동(MyBatis, Hibernate, JPA(Hibernate기반))
	 			4) Spring WEB : JSP 연동
	 			5) Spring MVC : View/Model/Controller

	 		***XML 사용
	 			스프링에서 객체를 생성하기 위해 사용한다.
	 			클래스명을 전송(ID) 이걸로 메모리할당이 가능
	 			----------
	 			XML
	 			Annotation			메타정보 -> 스프링 컨테이너
	 			자바코드
	 			----------
	 			xml 이용법
	 				1.클래스 한개씩 설정
	 					<bean id="a" class="A">
	 					<bean id="b" class="B">
	 					Map
	 					key		클래스 객체
	 					a		new A()
	 					b		new B()
	 					**프로그램 동작 시작 -> 클래스를 모아서 저장 : 주소값이 변경이 안된다
	 					  -> 싱글턴으로 하나만 생성하고 공통으로 접근
	 				2.패키지 단위로 설정 방법
	 					<context:component-scan base-package="com.sist.*">
	 					base-package 기반으로 경로를 읽고 안에 객체들을 bean으로 바꿔줌
	 				3.단점
	 					사용자 정의 / 라이브러리 클래스(MyBatis,ViewResolver...) => 어노테이션이 없다 -> 가급적이면 XML 등록
	 				
	 			Annotation
	 				메모리 할당
	 				@Component : 일반클래스(MainClass, 크롤링, OpenAPI)
	 				@Repository : DAO(저장소)
	 				@Service : DAO여러개를 한번에 통합(BI)
	 				@Controller : MVC구조의 Model(페이지 지정)
	 				@RestController :  MVC구조의 Model -> JSON
	 				@ControllerAdvice : 공통 예외처리
	 				@RestControllerAdvice : 공통 예외처리
	 				----------------------클래스에 설정을 하면 컨테이너가 메모리를 할당한다.
	 			***빈(객체) 생명주기
	 				빈생성 : DI = init-method = 객체사용(개발자) = destroy-method = 소멸
	 			자바코드
	 			
	 		3. AOP의 개념
	 		4. MVC구조 파악
	 		5. 보안
 			
 			
 			

  
  
 */
public class MainClass {
	public static void main(String[] args) {
		String path="C:\\springDev\\springStudy\\SpringDiProject_8\\src\\main\\java\\com\\sist\\main\\app.xml";
		ApplicationContext app=new ClassPathApplicationContext(path);
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println("사번:"+sa.getSabun());
		System.out.println("이름:"+sa.getName());
		System.out.println("부서:"+sa.getDept());
		System.out.println("직위:"+sa.getJob());
		 
		
	}

}
