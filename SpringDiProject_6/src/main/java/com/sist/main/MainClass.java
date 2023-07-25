package com.sist.main;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
import com.sist.vo.*;

public class MainClass {
	public static void main(String[] args) {
		
	}
	//오류 있음
	@Test //static 
	public void locationMain() {
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		List<SeoulLocationVO> list=dao.LocationListData();
		for(SeoulLocationVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
	}
	
	@Test
	public void locationDetail() {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("명소 번호 입력 : ");
		int no=sc.nextInt();
		
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		LocationDAO dao=(LocationDAO)app.getBean("ldao");
		SeoulLocationVO vo=dao.LocationDetailData(no);
		System.out.println("Title : "+vo.getTitle());
		System.out.println("Title : "+vo.getAddress());
		System.out.println("Title : "+vo.getMsg());
	}
	
	/*
	@Test
	public void NatureMain() {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		NatureDAO dao=(NatureDAO)app.getBean("ndao");
		List<SeoulNatureVO> list=dao.natureListData();
		for(SeoulNatureVO vo:list) {
			System.out.println(vo.getTitle()+". "+vo.getAddress());
		}
	
	}*/
	

}
