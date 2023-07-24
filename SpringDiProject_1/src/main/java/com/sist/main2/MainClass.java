package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	//xml파싱 가장먼저 
	ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
	
	Member m1=(Member)app.getBean("mem1");
	m1.print();
	Member m2=(Member)app.getBean("mem2");
	m2.print();
	Member m3=(Member)app.getBean("mem3");
	m3.print();

}
