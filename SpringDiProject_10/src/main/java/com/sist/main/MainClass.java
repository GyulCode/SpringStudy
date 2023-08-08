package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

@Component("mc")
public class MainClass {
	@Autowired //스프링에서 자동으로 객체 주소값을 찾아서 주입해 준다. 객체마다 적용해줘야 //getBean대용
	@Qualifier("bdao") //객체 지정 어노테이션
	private Board board;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
	}

}
