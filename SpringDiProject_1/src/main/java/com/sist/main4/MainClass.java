package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class MainClass {
	private Sawon sa;
	
	public Sawon getSa() {
		return sa;
	}

	public void setSa(Sawon sa) {
		this.sa = sa;
	}

	public static void main(String[] args) {
		
		//ApplicationContext app=new ClassPathXmlApplicationContext("app.xml4");
		GenericXmlApplicationContext app= new GenericXmlApplicationContext("app4.xml"); //위에꺼 다음버전 close할려고 사용
		
		Sawon sa=(Sawon)app.getBean("sa1");
		//sa.init()
		sa.print();
		//sa.destroy() - 스프링이 닫겨야 수행됨
		app.close();
		

	}

}
