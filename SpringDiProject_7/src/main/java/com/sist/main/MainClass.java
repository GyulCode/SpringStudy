package com.sist.main;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.vo.*;

//������̼����� ������ ���� �ڵ�� ���� �Ҵ�
//<bean id="MainClass" class="com.sist.main.MainClass">
// id�� ���� ��쿡�� class���� ���̵�� �����ȴ� (ù�ڸ� �ҹ���)
// EmpDAO -> empDAO

@Component("mc")
public class MainClass {
	@Autowired // -> getBean() ��ü�� ����ϴ°� ������̾�� �̰� ��ü�� �ֱ���
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
		System.out.println("��� : ");
		int empno=sc.nextInt();
		
		EmpVO vo=mc.dao.empDetailData(empno);
		System.out.println("==== ��� ���� =====");
		System.out.println("���"+vo.getEmpno() );
		System.out.println("�̸�"+vo.getEname() );
		System.out.println("�Ի���"+vo.getDbday() );
		System.out.println("����"+vo.getJob() );
		System.out.println("�޿�"+vo.getDbsal() );
		System.out.println("������"+vo.getComm() );
		*/
		
		Scanner sc=new Scanner(System.in);
		System.out.println("����� : ");
		String name=sc.next();
		list=mc.dao.empDetailSearch(name);
		for(EmpVO vo:list) {
			if(vo==null) {
				System.out.println("���� ������ϴ�.");
			}
			System.out.println("==== ��� ���� =====");
			System.out.println(vo);
			System.out.println("���"+vo.getEmpno() );
			System.out.println("�̸�"+vo.getEname() );
			System.out.println("�Ի���"+vo.getDbday() );
			System.out.println("����"+vo.getJob() );
			System.out.println("�޿�"+vo.getDbsal() );
			System.out.println("������"+vo.getComm() );
		}
	} 
	
	
	

}
