package com.sist.main;
/*
 * �̹� ���۵Ǿ� �ִ� ���̺귯��
 *  ������
 *  1.xml
	2.annotation
	3.xml + annotation(�̰� �߼�)
 * ---------------------
 * 1. Container : ����ڰ� ����� Ŭ������ ���� (POJO ��� : �Ϲ� Ŭ����, �������̽�, ����� ���� )
 * 2. DI : Ŭ������ Ŭ�������� �������� ���� / ������ �ʱ�ȭ, �ʿ��� �޼ҵ� ȣ��
 * 2-1. XML�� ���(�޴���)
 * -> XML, Annotation -> Container -> ���α׷� ������ ����
 * -> ����(XML�� ����)
 * -> Container
 * 		1. BeanFactory
 * 		2. ApplicationContext(XML)
 * 			�����ϰ� �ڹ�(����)�� ���� �̰Ŵ� spring5����(����) ���� ����
 * 			AnnotationConfigApplicationContext
 * 		3.WebApplicationContext
 * -> ������� (Ŭ���� �޸� �Ҵ�)
 * <bean id="" Class=""> -> Class.forName("A") -> ã�� ������ ID, ID�� �ߺ��� �Ұ��� �ϴ�
 * ������ ����(DI) : class�� �����ϱ� ���� �������(�Ϲݺ���[�⺻��int, double, string...], Ŭ���� ��ü(�ּ�)) �ʱ�ȭ
 *    ***�Ϲ� ��������� �����ͺ��̽� �����ǿ��� ���� ��� �󵵰� ����.
 * p : name -> property �Ϲݺ��� ����
 * p : dao-ref -> ������ ��ü�� �ּҰ� ����
 * init-method : ������ ���ÿ� �ڵ����� �޼ҵ� ȣ��, ũ�Ѹ�, Ʈ���� �б�, �ڵ� �α���, ����̹� ���
 * �����ڴ� ��ϵ� Ŭ���� ��ü�� Ȱ��
 * destroy-method : ��ü �Ҹ�� ȣ�� db.close()....
 * DI(�������� ���ؼ� ���α׷� ������ ���� �ʿ��� �����͸� ����)
 * 
 * Data�� DAO���� <- �̰� �������� DAO ��ü�� �ּҸ� �޾Ƽ� DAO������ �Ѵ�.
 * Ŭ������ ������ ������ ���� ���� ����
 * 1. ����� �ʱ�ȭ(x)
 * 2. ������ �Ű������� ���� ����
 * 3. setter(property) / setXXX()�� ���� ����
 * c: �Ű������� �̿��ؼ� ���� �ִ´�
 * Ŭ������ �������� xml�� Ŀ����(�ӵ��� �ʾ���)
 * -> ��Ű�� ������ ��� -> ���������� ��� ������ ������̼����� �Ѵ�.
 * 
 * 1. ����� ���� Ŭ����
 * 2. ���̺귯�� Ŭ���� (<BEAN>)
 * -------------------------
 * XML : �����Ѱ��� �������� ����Ѵ�(���� ���߽� ���)
 * Annotation : Ŭ���� ���� ���� ����(������ ���� ����)
 * ->*** ���� �Ұ� MyBatis�� �����ϰ� annotation���
 * MyBatis���� XML, Annotation, Xml+Annotation 3���� ������� ���𰡴�
 * 
 * 
 * 
 * 
 *       
 * 3. AOP :  ���� ����� ��Ƽ� ����(�ߺ� ����)
 * 4. MVC : �� ����(������ ���� + ȭ�����)
 * 5. Front + Back : Restful(�������� ��� json���)
 * 6. ����(����, ��ȣȭ, ��ȣȭ)
 * ----------------------
 * ��Ÿ : �����ͺ��̽� (ORM) -> MyBatis, Ʈ�����
 * 
 * 
 */

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass {
	
	public static void main(String[] args) {
		//spring �� ���
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "
					+ vo.getEname()+" "
					+ vo.getJob()+" "
					+ vo.getDeptno()+" "
					);
		}
		System.out.println("==============");
		Scanner sc=new Scanner(System.in);
		System.out.println("��� �Է� : ");
		int empno=sc.nextInt();
		EmpVO vo=dao.empDetailData(empno);
		System.out.println("======= ��� ���� =======");
		System.out.println("��� : "+vo.getEmpno());
		System.out.println("�̸� : "+vo.getEname());
		System.out.println("���� : "+vo.getJob());
		System.out.println("����� : "+vo.getMgr());
		System.out.println("�Ի���1 : "+vo.getHiredate());
		System.out.println("�Ի��� : "+vo.getDbday()); //vo.hiredate()
		System.out.println("�޿� : "+vo.getSal());
		System.out.println("������ : "+vo.getComm());
		System.out.println("�μ� : "+vo.getDeptno());
	}
	
			

}
