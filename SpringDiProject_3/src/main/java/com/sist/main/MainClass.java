package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Setter;
public class MainClass {
	@Setter
	private GoodsDAO dao;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext();
		//MainClass mc =new MainClass(); 이코드는 오류
		MainClass mc=(MainClass)app.getBean("mc");
		 
		List<String> list=mc.dao.goodsNameList(); 
		for(String name:list) {
			System.out.println(name);
		}
				
		
	}
	
	

}
