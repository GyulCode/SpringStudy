package com.sist.main;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sist.config.EmpConfig;
import com.sist.dao.*;
@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app= new AnnotationConfigApplicationContext(EmpConfig.class); //제네릭? 어노테이션?
		
		MainClass mc=(MainClass)app.getBean("mc");
		
		List<EmpVO>	list=mc.dao.empListData();
		
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname());
		}
		
		Scanner sc=new Scanner(System.in);
		System.out.println("사번을 입력하세요 : ");
		int empno=sc.nextInt();
		EmpVO vo=mc.dao.empDetailData(empno);
		System.out.println(vo.getEmpno());
		System.out.println(vo.getEname());
		System.out.println(vo.getSal());
		System.out.println(vo.getHiredate());
		
		
		
		
	}
	
	

}
