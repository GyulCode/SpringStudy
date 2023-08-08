package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		BoardDAO dao=(BoardDAO)app.getBean("dao");
		dao.aopDelete();
		dao.aopUpdate();
		dao.aopSelect("");
		dao.aopInsert();
		String name=dao.find("Ω…√ª¿Ã");

		
		 
	}
}
