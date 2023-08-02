package com.sist.main;

import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		//�Ľ� -> ��ϵ� Ŭ������ ������ �����̳ʷ� ����
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
		System.out.println("��� �Է�:");
		int empno=sc.nextInt();
		EmpVO vo=dao.empdeptDetailData(empno);
		System.out.println("���:");
		System.out.println("�̸�");
		System.out.println("����");
		System.out.println("�Ի���:");
		System.out.println("�޿�"+vo.getSal());
		System.out.println("�μ���:"+vo.getDvo().getDname());
		System.out.println("�ٹ���");
		
	}

}
