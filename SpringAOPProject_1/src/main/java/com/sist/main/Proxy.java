package com.sist.main;

public class Proxy {
	
	private Sawon sawon;
	
	public Proxy(Sawon sawon)
	{
		this.sawon=sawon;
	}
	
	public void display()
	{
		System.out.println("Before: 刚历 贸府");
		sawon.display();
		System.out.println("After: 唱吝俊 贸府");
	}
}
