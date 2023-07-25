package com.sist.main;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;

//어노테이션으로 다음과 같은 코드와 같게 할당
//<bean id="MainClass" class="com.sist.main.MainClass">
// id가 없는 경우에는 class명이 아이디로 설정된다 (첫자만 소문자)
// EmpDAO -> empDAO

@Component("mc")
public class MainClass {
	@Autowired // -> getBean() 대체로 사용하는게 오토와이어드 이거 대체도 있긴함
	private EmpDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc");
		
		List<EmpVO> list= mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getDbsal()+" ");
		}
		System.out.println("==================");
		
		/*
		Scanner sc=new Scanner(System.in);
		System.out.println("사원 : ");
		int empno=sc.nextInt();
		
		EmpVO vo=mc.dao.empDetailData(empno);
		System.out.println("==== 사원 정보 =====");
		System.out.println("사번"+vo.getEmpno() );
		System.out.println("이름"+vo.getEname() );
		System.out.println("입사일"+vo.getDbday() );
		System.out.println("직위"+vo.getJob() );
		System.out.println("급여"+vo.getDbsal() );
		System.out.println("성과급"+vo.getComm() );
		*/
		
		Scanner sc=new Scanner(System.in);
		System.out.println("사원명 : ");
		String name=sc.next();
		list=mc.dao.empDetailSearch(name);
		for(EmpVO vo:list) {
			if(vo==null) {
				System.out.println("값이 비었습니다.");
			}
			System.out.println("==== 사원 정보 =====");
			System.out.println(vo);
			System.out.println("사번"+vo.getEmpno() );
			System.out.println("이름"+vo.getEname() );
			System.out.println("입사일"+vo.getDbday() );
			System.out.println("직위"+vo.getJob() );
			System.out.println("급여"+vo.getDbsal() );
			System.out.println("성과급"+vo.getComm() );
		}
	} 
	
	
	

}
