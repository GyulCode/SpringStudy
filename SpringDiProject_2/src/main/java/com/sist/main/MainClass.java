package com.sist.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml"); //라이브러리
		
		//EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver"); new는 쓰면 안됨 이미 세팅을 다 해서 주는데 새롭게 설정한다는게 되버린다.
		EmpDAO dao=(EmpDAO)app.getBean("dao");//
		
		List<EmpVO>list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}

}
