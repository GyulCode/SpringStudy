package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		// xml  �Ľ�
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		Sawon s1=(Sawon)app.getBean("sa1"); 
		System.out.println("���:"+s1.getSabun());
		System.out.println("�̸�:"+s1.getName());
		System.out.println("�μ�:"+s1.getDept());
		System.out.println("����:"+s1.getJob());
		System.out.println("����:"+s1.getPay());
		System.out.println();
		
		Sawon s2=app.getBean("sa1",Sawon.class); //���׸���
		s2.setSabun(2);
		s2.setName("��û��");
		s2.setDept("�ѹ���");
		s2.setJob("����");
		s2.setPay(4000);
		
		System.out.println("���:"+s1.getSabun());
		System.out.println("�̸�:"+s1.getName());
		System.out.println("�μ�:"+s1.getDept());
		System.out.println("����:"+s1.getJob());
		System.out.println("����:"+s1.getPay());
		System.out.println("S1:"+s1); 
		System.out.println("S2:"+s2); 
		System.out.println();
		
		
		
		
		
		
		
		
	}

}
