package com.sist.aop;

/*
 * aspect : AOP�� OOP��ü�� �ƴ�(������), �������� ��Ƶ� ������ aspect�� ����
 * **OOP : �ݹ��Լ��� ����(�ý��ۿ� ���� �ڵ�ȣ�� main, sevice�����ɷ�)
 * 		��, �ΰ� �������� ���Ǵ� ������ �޼ҵ�� ó���Ѵ�. getConnection, disConnection
 * 		�������� �����̸� Ŭ������ ���� CreateDataBase
 * 		------------------������
 * ������������ �ݺ����� �ڵ��� ��Ƽ� ���� -> �ʿ�ÿ� �ڵ� ȣ�⵵ ���� -> AOP
 * ***����� ���Ǵ� ���� ������� �ʴ´�. ��� ���̺귯���� �����(���̺귯�� : Ʈ�����, ����, �α�����)
 * 
 * AOP
 * ---
 * 1. ��޼ҵ忡 �����ų ���ΰ� : Pointcut(������ �޼ҵ� ����)
 * -> Pointcut = "execute( * com.sist.main.*.*(..))"
 *                         --			   --- --
 *                         ������		    Ŭ������, �޼ҵ�� (..) �Ű�����x, �Ű�����
 * -> Pointcut ="within("com.sist.main.*")" 
 * 	             ------��Ű�� ������ ���
 *  
 * 2. ȣ��Ǵ� ��ġ���� : JoinPoint
 * @Transectional -> �����̷��� ����� ������̼����� �ذ�
 * public String display(){
 * 		@Before : getConnection()
 * 		try{
 * 			----------@Around : setAutoCommit(false) 
 * 			�ٽ� �ڵ�
 * 			----------@Around : Commit()
 * 		}catch{
 * 			@After-Throwing : ����ó�� : rollback()
 * 		}finally{
 * 			@After : setAutoCommit(true), disConnection()
 * 		}
 * 		retrun : @AFter-Returning
 * }
 * 
 * JoinPoint + Pointcut -> Advice -> aspect
 * Weaving : ���� -> Proxy ����(�븮��)
 * 
 * public Class A
 * {
 * 		public void display(){
 * 			
 * 		}
 * }
 * 
 * public Class Proxy
 * {
 * 		private A a;
 * 		public Proxy( A a) {
 * 			this.a=a;
 * 		}
 * 		public void display()
 * 		{
 * 			@Before -> ������ �޼ҵ� ȣ��
 * 			a.display();
 * 			@After -> ������ �޼ҵ� ȣ��
 * 		}
 * }
 * -> �̷��� ���¸� �����̶� �Ѵ�.
 * 
 * 
 */
import java.util.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

@Aspect //������ �������� ���
@Component //�޸� �Ҵ翪��
public class EmpAspect {
	 
	@Autowired
	private EmpDAO dao;
	
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))") //try ���۰� ���ÿ� ȣ��
	public void getConnection( ) {
		System.out.println("DB����");
		dao.getConnection();
	}
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))") //Finally���� ȣ��
	public void disConnection(Object ex) {
		System.out.println("DB����");
		dao.disConnection();
	}
	
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp*(..))",returning ="ex") //���� ���� �� return���� �޾Ƽ� ó��
	public void print(Object obj) {
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getSal()+" "
					);
		}
	}
	
	@AfterThrowing(value="execution(* com.sist.dao.EmpDAO.emp*(..))",throwing ="ex") //catch�� ����� ó��
	public void cat(Throwable ex) {
		
	}
	
	
	
	

}
