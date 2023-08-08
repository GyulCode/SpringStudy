package com.sist.aop;
/*
 * ���� ��� ȣ������ ���� Ȯ��
 * 1. ���� -> pointcut -> �޼ҵ����� ���⼭ �����ض�~
 * 2. ȣ����ġ -> Joinpoint
 * @Before : try �������� ȣ��
 * @After : finally
 * @Around : �� �Ʒ� -> �α�����(����, ��, �ð� üũ), Ʈ����� ó��
 * @After - Throwing : catch(�����߻�)
 * @After-Returning : ��������� 
 * 
 * 
 * @Transactional -> �� ������̼��� ������ AOP������ �ȴ�. 
 * public void display(){
 * 	try{
 * 			@Before
 * 			@Around -> conn.setAutoCommit(false)
 * 
 * 			--�ٽ� �ҽ��ڵ�--
 * 						---
 * 
 * 	} catch(Exception ex){
 * 			@After-Throwing conn.rollback()
 * 	} finally {
 * 			@After conn.setAutoCommit();
 * 	}
 * 		return ""; @After-Returning
 * 
 * }
 * ----------------------advice
 * 
 * ----------------------aspect
 * 
 */
public class BoardAspect {
	public void before() { //����������� ȣ��
		System.out.println("����Ŭ ����");
	}
	
	public void after() { //������ ���� -> finally
		System.out.println("����Ŭ ����");
		
	}

}
