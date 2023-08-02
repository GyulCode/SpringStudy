package com.sist.main;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		//파싱 -> 등록된 클래스를 스프링 컨테이너로 전송
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		
		//DL(getBean, @Autowired) / DI
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empdeptListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" "
					+vo.getSal()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc());
		}
		System.out.println("==========");
		Scanner sc=new Scanner(System.in);
		System.out.println("사번 입력:");
		int empno=sc.nextInt();
		EmpVO vo=dao.empdeptDetailData(empno);
		System.out.println("사번:");
		System.out.println("이름");
		System.out.println("직위");
		System.out.println("입사일:");
		System.out.println("급여"+vo.getSal());
		System.out.println("부서명:"+vo.getDvo().getDname());
		System.out.println("근무지");
		
	}

}
