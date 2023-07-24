package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  컨테이너(스프링이 주로 하는 역할)
 *  -> 개발자가 만드는 클래스(한개의 기능 수행: 컴포넌트)
 *  컴포넌트 여러개를 관리하는 역할(컨테이너)
 *  Jbutton, JTextField...(컴포넌트)
 *  ----------------------JFram(컨테이너)
 *  컨테이너의 역할
 *  1) 객체(컴포넌트) 생성 -> new(##new사용 VO:사용자정의 데이터형);
 *  2) 객체의 변수 초기화 == DI
 *  3) 객체의 소멸
 *  ----객체의 생명주기
 *  -> 핵심 기능에만 집중해서 개발할 수 있게해줌
 *  
 *    // 아래는 스프링 컨테이너의 종류중 하나이다.
 *   BeanFactory === core(DI)
 *   ApplicationContext (Application) -> core(DI), AOP ***자주 사용함
 *      | -AnnotationConfigApplicationContext
 *         Spring 5버전의 핵심 -> (XML을 사용하지 않고 순수하게 자바만)
 *   WebApplicationContext(MVC) -> core(DI), AOP, MVC 까지지원
 *  
 *  
 *  
 */

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		//ClassPathXmlApplicationContext() xml을 파싱(xml에 등록된 클래스를 읽어간다)
		//ClassPath : src/main/java 이부분까지 자동인식
		A a=(A)app.getBean("a");
		a.display();
		
		

	}

}
