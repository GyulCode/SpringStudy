package com.sist.main2;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.config.EmpConfig;
import com.sist.dao2.*;
import com.sist.vo.*;

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
//		ApplicationContext app= new ClassPathXmlApplicationContext("app2.xml");
		AnnotationConfigApplicationContext app= new AnnotationConfigApplicationContext(EmpConfig.class);
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empdeptListData();
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
		System.out.println("��� �Է�:");
		int empno=sc.nextInt();
		EmpVO vo=mc.dao.empdeptDetailData(empno);
		System.out.println("���:"+vo.getEmpno());
		System.out.println("�̸�"+vo.getEname());
		System.out.println("����"+vo.getJob());
		System.out.println("�Ի���:"+vo.getDbday());
		System.out.println("�޿�"+vo.getSal());
		System.out.println("�μ���:"+vo.getDvo().getDname());
		System.out.println("�ٹ���");
		
	}

}
