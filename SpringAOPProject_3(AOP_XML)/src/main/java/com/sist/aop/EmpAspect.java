package com.sist.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.dao.EmpVO;

/*
 * 1.before
 * 2.after
 * --------
 * 3.around
 * 4.after-returning
 * 5.after-throwing
 * 
 * AOP �޴������� ���� �α��� �ʿ��ϴ�~ �̷��� ��½� ���
 * 
 */

public class EmpAspect {
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("Around call...try����, try����");
		/*
		try {
			-----------���⼭ ó��
			�ٽ�
			-----------���⼭ ó��
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		Object obj=null;
		long start=0, end=0;
		
		start=System.currentTimeMillis();//������
		System.out.println("����ڰ� ȣ���� �޼ҵ�� : "+jp.getSignature().getName());
		obj=jp.proceed(); //�޼ҵ� ȣ�� -> invoke()
		
		end=System.currentTimeMillis();//������
		System.out.println("���� �ð� : "+(end-start));
		
		
		return obj;
	}
	
	//���� ������ ���ϰ��� �޾Ƽ� ó��
	public void afterReturning(Object obj) throws Throwable
	{
		System.out.println("afterReturning Call.. �������");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" ");
		}
	}
	
	//catch�� �����
	public void afterThrowing(Throwable ex) throws Throwable{
		System.out.println("afterThrowing Call...");
		ex.printStackTrace();
		
	}
}
