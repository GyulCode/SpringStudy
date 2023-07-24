package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 *  �����̳�(�������� �ַ� �ϴ� ����)
 *  -> �����ڰ� ����� Ŭ����(�Ѱ��� ��� ����: ������Ʈ)
 *  ������Ʈ �������� �����ϴ� ����(�����̳�)
 *  Jbutton, JTextField...(������Ʈ)
 *  ----------------------JFram(�����̳�)
 *  �����̳��� ����
 *  1) ��ü(������Ʈ) ���� -> new(##new��� VO:��������� ��������);
 *  2) ��ü�� ���� �ʱ�ȭ == DI
 *  3) ��ü�� �Ҹ�
 *  ----��ü�� �����ֱ�
 *  -> �ٽ� ��ɿ��� �����ؼ� ������ �� �ְ�����
 *  
 *    // �Ʒ��� ������ �����̳��� ������ �ϳ��̴�.
 *   BeanFactory === core(DI)
 *   ApplicationContext (Application) -> core(DI), AOP ***���� �����
 *      | -AnnotationConfigApplicationContext
 *         Spring 5������ �ٽ� -> (XML�� ������� �ʰ� �����ϰ� �ڹٸ�)
 *   WebApplicationContext(MVC) -> core(DI), AOP, MVC ��������
 *  
 *  
 *  
 */

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app= new ClassPathXmlApplicationContext("app.xml");
		//ClassPathXmlApplicationContext() xml�� �Ľ�(xml�� ��ϵ� Ŭ������ �о��)
		//ClassPath : src/main/java �̺κб��� �ڵ��ν�
		A a=(A)app.getBean("a");
		a.display();
		
		

	}

}
