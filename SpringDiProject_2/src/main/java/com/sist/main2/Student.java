package com.sist.main2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private int hakbun;
	private String name,sex;
	
	public void init() {
		System.out.println("========= �л� ���� ==========");
		
	}
	public void print() {
		System.out.println("�й�:"+hakbun);
		System.out.println("�̸�:"+name);
		System.out.println("����:"+sex);
	}
}
