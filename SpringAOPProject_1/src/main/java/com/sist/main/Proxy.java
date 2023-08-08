package com.sist.main;

public class Proxy {
	
	private Sawon sawon;
	
	public Proxy(Sawon sawon)
	{
		this.sawon=sawon;
	}
	
	public void display()
	{
		System.out.println("Before: ���� ó��");
		sawon.display();
		System.out.println("After: ���߿� ó��");
	}
}
