package com.sist.main2;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/*
 * 1. 메모리에 할당 -> 생성자 호출
 * 2. setter DI -> set.xxx()값을 채운다
 * 3. init-method에 등록된 메소드 호출
 * 4. 개발자에 의해 필요한 메소드 호출
 * 5. destroy-method 메모리 해제 
 * 
 */
public class Sawon implements BeanNameAware, InitializingBean, DisposableBean{
	private int sabun;
	private String name, dept;
	// <bean id="sa" class="Sawon"> 이 태그를 읽을때 메모리에 할당함 xml쪽인듯?
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
		System.out.println("========= 사원 정보 ==========");
		
	}
	public void print() {
		System.out.println("사번:"+sabun);
		System.out.println("이름:"+name);
		System.out.println("부서:"+dept);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Setter 처리 종료후 호출이 되는 부분");
		
	}
	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.out.println("setBeanName():"+name);
		
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.print("객체 소멸");
	}
	
	

}
