package com.sist.main2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/*
 * 1. �޸𸮿� �Ҵ� -> ������ ȣ��
 * 2. setter DI -> set.xxx()���� ä���
 * 3. init-method�� ��ϵ� �޼ҵ� ȣ��
 * 4. �����ڿ� ���� �ʿ��� �޼ҵ� ȣ��
 * 5. destroy-method �޸� ���� 
 * 
 */
public class Sawon implements BeanNameAware, InitializingBean, DisposableBean{
	private int sabun;
	private String name, dept;
	// <bean id="sa" class="Sawon"> �� �±׸� ������ �޸𸮿� �Ҵ��� xml���ε�?
	public Sawon() {
		System.out.println("Sawon() Call...");
	}
	
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		System.out.println("setSabun() Call..."+sabun); 
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName() Call..."+name);
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		System.out.println("setDept() Call..."+dept);
		this.dept = dept;
	}
	public void init() {
		System.out.println("init() Call...");
		System.out.println("========= ��� ���� ==========");
		
	}
	public void print() {
		System.out.println("���:"+sabun);
		System.out.println("�̸�:"+name);
		System.out.println("�μ�:"+dept);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Setter ó�� ������ ȣ���� �Ǵ� �κ�");
		
	}
	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.out.println("setBeanName():"+name);
		
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.print("��ü �Ҹ�");
	}
	
	

}
