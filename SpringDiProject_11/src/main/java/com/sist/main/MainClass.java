package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;

import sun.print.resources.serviceui;

@Component("mc") //디폴트 네임은 첫자만 소문자로 
public class MainClass {
	@Autowired
	private FoodService service;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Scanner sc=new Scanner(System.in);
		// 메인클래스를 사용하기위해 메인클래스 선언
		MainClass mc=(MainClass)app.getBean("mc"); //component에 값을 안주면 default MainClass가 대입됨
		List<CategoryVO> list=mc.service.cateListData();
		
		for(CategoryVO vo:list) {
			System.out.println(vo.getCno()+" "+vo.getTitle());
		}
		
		System.out.println("------------------------------");
		System.out.println("카테고리 번호 선택:");
		int cno=sc.nextInt();
		List<FoodVO> flist=mc.service.foodCategoryListData(cno);
		for(FoodVO vo:flist) {
			System.out.println(vo.getFno()+" "+vo.getName());
		}
		
		System.out.println("------------------------------");
		System.out.println("맛집 번호 선택 : ");
		int fno=sc.nextInt();
		FoodVO vo=mc.service.foodDetailData(fno);
		System.out.println("맛집명 : "+vo.getName());
		System.out.println("주소 : "+vo.getAddress());
		System.out.println("전화 : "+vo.getPhone());
		System.out.println("음식종류 : "+vo.getType());
		System.out.println("가격 : "+vo.getPrice());
		System.out.println("주차 : "+vo.getParking());
		System.out.println("메뉴 : "+vo.getMenu());
		
				
		 
		
		
	}

}
