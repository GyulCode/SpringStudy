package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;

import java.lang.invoke.CallSite;
import java.util.*;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		StudentDAO dao=app.getBean("dao",StudentDAO.class); //getBean�� ����� DL�̰� "dao" �޺κ� ���׸��� 
		
		Scanner sc=new Scanner(System.in);
		
		
		while(true) {
			System.out.println("==========�޴�=========");
			System.out.println("1.�л����");
			System.out.println("2.�󼼺���");
			System.out.println("3.�˻�");
			System.out.println("4.�߰�");
			System.out.println("5.����");
			System.out.println("6.����");
			System.out.println("0.���α׷� ����");
			System.out.println("======================");
			System.out.print("�޴����� : ");
			int no=sc.nextInt();
			switch(no) {
			case 1:
			{
				List<StudentVO> list=dao.studentListData();
				if(list.size()==0) {
					System.out.println("��ϵ� �л��� �����ϴ�.");
				}else {
					for(StudentVO vo:list) {
						System.out.println(vo.getHakbun()+" "
								+vo.getName()+" "
								+vo.getKor()+" "
								+vo.getMath()+" "
								+vo.getEng()+" "
								);
					}
				}
			}
				break;
			case 2:
			{
				System.out.println("�󼼺� �л��� �й��� �Է��ϼ��� : ");
				int hakbun = sc.nextInt();
				StudentVO vo=dao.studentDetailData(hakbun);
				System.out.println(vo.getHakbun());
				System.out.println(vo.getName());
				System.out.println(vo.getKor());
				System.out.println(vo.getMath());
				System.out.println(vo.getEng());
				System.out.printf("���� : %d \n",(vo.getKor()+vo.getEng()+vo.getMath()));
				System.out.printf("��� : %.2f \n",(vo.getKor()+vo.getEng()+vo.getMath())/3.0);
				
			}
				break;
			case 3:
			{
				System.out.println("�л� �̸� �Է� : ");
				String name=sc.next();
				List<StudentVO> list=dao.studentFindData(name);
				if(list.size()==0) {
					System.out.println("��ϵ� �л��� �����ϴ�.");
				}else {
					for(StudentVO vo:list) {
						System.out.println(vo.getHakbun()+" "
								+vo.getName()+" "
								+vo.getKor()+" "
								+vo.getMath()+" "
								+vo.getEng()+" "
								);
					}
				}
			}
				break;
			case 4:
			{
				System.out.println("�̸� �Է�:");
				String name=sc.next();
				System.out.println("���� �Է�:");
				int kor=sc.nextInt();
				System.out.println("���� �Է�:");
				int math=sc.nextInt();
				System.out.println("���� �Է�:");
				int eng=sc.nextInt();
				
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setMath(math);
				vo.setEng(eng);
				
				dao.studentInsert(vo);
				System.out.println("��ϿϷ�");
			}
				break;
			case 5:
			{
        		System.out.print("�̸� �Է�:");
        		String name=sc.next();
        		System.out.print("���� �Է�:");
        		int kor=sc.nextInt();
        		System.out.print("���� �Է�:");
        		int eng=sc.nextInt();
        		System.out.print("���� �Է�:");
        		int math=sc.nextInt();
        		System.out.print("�й� �Է�:");
        		int hakbun=sc.nextInt();
        		
        		StudentVO vo=new StudentVO();
        		vo.setName(name);
        		vo.setKor(kor);
        		vo.setMath(math);
        		vo.setEng(eng);
        		vo.setHakbun(hakbun);
        		
        		dao.studentUpdate(vo);
        		System.out.println("���� �Ϸ�");
        	}
				break;
			case 6:
			{
				System.out.println("������ �й��� �Է��ϼ��� : ");
				int hakbun=sc.nextInt();
				dao.studentDelete(hakbun);
			}
				break;
			case 0:
				System.exit(0);
			}
		}
	}

}
