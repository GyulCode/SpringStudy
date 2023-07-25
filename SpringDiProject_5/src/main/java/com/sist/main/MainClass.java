package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		SeoulDAO dao=(SeoulDAO)app.getBean("dao");
		FoodDAO dao2=(FoodDAO)app.getBean("dao2");
		
		int curpage=1;
		Scanner sc=new Scanner(System.in);
		System.out.println("������ �Է� : ");
		curpage=sc.nextInt();
		
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		
		List<FoodVO> list= dao2.foodListData(map);
		System.out.println("=== ���� ī�װ� �з�===");
		for(FoodVO vo:list) {
			System.out.println(vo.getTitle()+" "+vo.getSubject());
		}
		
		/*
		List<SeoulLocationVO> list = dao.seoulListData(map);
		System.out.println("===���� ���===");
		for(SeoulLocationVO vo:list) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
		System.out.println("====================");
		int totalpage=dao.seoulTotalPage();
		System.out.println("  "+curpage+" page / "+totalpage+" pages");
		System.out.println("====================");
		
		System.out.println("�˻��� �Է� : ");
		String title=sc.next();
		List<SeoulLocationVO> fList=dao.seoulFindData(title);
		
		System.out.println("========�˻� ���==========");
		for(SeoulLocationVO vo:fList) {
			System.out.println(vo.getNo()+"."+vo.getTitle());
		}
		*/
		
		
	}

}
