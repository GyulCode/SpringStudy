package com.sist.main3;

public class MainClass {
	public static void main(String[] args) {
		//알아보기
		ApplicationContext app= new ApplicationContext();
		A a=(A)app.getBean("a");
		a.display();
		
		B b=(B)app.getBean("b");
		b.display();
		
	}

}
