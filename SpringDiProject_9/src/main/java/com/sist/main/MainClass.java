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
		StudentDAO dao=app.getBean("dao",StudentDAO.class); //getBean을 사용한 DL이고 "dao" 뒷부분 제네릭스 
		
		Scanner sc=new Scanner(System.in);
		
		
		while(true) {
			System.out.println("==========메뉴=========");
			System.out.println("1.학생목록");
			System.out.println("2.상세보기");
			System.out.println("3.검색");
			System.out.println("4.추가");
			System.out.println("5.수정");
			System.out.println("6.삭제");
			System.out.println("0.프로그램 종료");
			System.out.println("======================");
			System.out.print("메뉴선택 : ");
			int no=sc.nextInt();
			switch(no) {
			case 1:
			{
				List<StudentVO> list=dao.studentListData();
				if(list.size()==0) {
					System.out.println("등록된 학생이 없습니다.");
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
				System.out.println("상세볼 학생의 학번을 입력하세요 : ");
				int hakbun = sc.nextInt();
				StudentVO vo=dao.studentDetailData(hakbun);
				System.out.println(vo.getHakbun());
				System.out.println(vo.getName());
				System.out.println(vo.getKor());
				System.out.println(vo.getMath());
				System.out.println(vo.getEng());
				System.out.printf("총점 : %d \n",(vo.getKor()+vo.getEng()+vo.getMath()));
				System.out.printf("평균 : %.2f \n",(vo.getKor()+vo.getEng()+vo.getMath())/3.0);
				
			}
				break;
			case 3:
			{
				System.out.println("학생 이름 입력 : ");
				String name=sc.next();
				List<StudentVO> list=dao.studentFindData(name);
				if(list.size()==0) {
					System.out.println("등록된 학생이 없습니다.");
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
				System.out.println("이름 입력:");
				String name=sc.next();
				System.out.println("국어 입력:");
				int kor=sc.nextInt();
				System.out.println("수학 입력:");
				int math=sc.nextInt();
				System.out.println("영어 입력:");
				int eng=sc.nextInt();
				
				StudentVO vo=new StudentVO();
				vo.setName(name);
				vo.setKor(kor);
				vo.setMath(math);
				vo.setEng(eng);
				
				dao.studentInsert(vo);
				System.out.println("등록완료");
			}
				break;
			case 5:
			{
        		System.out.print("이름 입력:");
        		String name=sc.next();
        		System.out.print("국어 입력:");
        		int kor=sc.nextInt();
        		System.out.print("영어 입력:");
        		int eng=sc.nextInt();
        		System.out.print("수학 입력:");
        		int math=sc.nextInt();
        		System.out.print("학번 입력:");
        		int hakbun=sc.nextInt();
        		
        		StudentVO vo=new StudentVO();
        		vo.setName(name);
        		vo.setKor(kor);
        		vo.setMath(math);
        		vo.setEng(eng);
        		vo.setHakbun(hakbun);
        		
        		dao.studentUpdate(vo);
        		System.out.println("수정 완료");
        	}
				break;
			case 6:
			{
				System.out.println("삭제할 학번을 입력하세요 : ");
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
