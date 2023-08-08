package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;

import sun.print.resources.serviceui;

@Component("mc") //����Ʈ ������ ù�ڸ� �ҹ��ڷ� 
public class MainClass {
	@Autowired
	private FoodService service;
	
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		Scanner sc=new Scanner(System.in);
		// ����Ŭ������ ����ϱ����� ����Ŭ���� ����
		MainClass mc=(MainClass)app.getBean("mc"); //component�� ���� ���ָ� default MainClass�� ���Ե�
		List<CategoryVO> list=mc.service.cateListData();
		
		for(CategoryVO vo:list) {
			System.out.println(vo.getCno()+" "+vo.getTitle());
		}
		
		System.out.println("------------------------------");
		System.out.println("ī�װ� ��ȣ ����:");
		int cno=sc.nextInt();
		List<FoodVO> flist=mc.service.foodCategoryListData(cno);
		for(FoodVO vo:flist) {
			System.out.println(vo.getFno()+" "+vo.getName());
		}
		
		System.out.println("------------------------------");
		System.out.println("���� ��ȣ ���� : ");
		int fno=sc.nextInt();
		FoodVO vo=mc.service.foodDetailData(fno);
		System.out.println("������ : "+vo.getName());
		System.out.println("�ּ� : "+vo.getAddress());
		System.out.println("��ȭ : "+vo.getPhone());
		System.out.println("�������� : "+vo.getType());
		System.out.println("���� : "+vo.getPrice());
		System.out.println("���� : "+vo.getParking());
		System.out.println("�޴� : "+vo.getMenu());
		
				
		 
		
		
	}

}
