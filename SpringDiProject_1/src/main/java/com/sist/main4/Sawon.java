package com.sist.main4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sawon {
	private int sabun, pay;
	private String name, dept, job;
	public Sawon() {
		System.out.println("����� ������ ȣ��");
	}
	
	
	
	public void init() {
		System.out.println("==========��� ����===========init");
	}
	public void print() {
		System.out.println("��� : "+sabun);
		System.out.println("�̸� : "+name);
		System.out.println("�μ� : "+dept);
		System.out.println("���� : "+job);
		System.out.println("���� : "+pay);
	}
	public void destroy() {
		System.out.println("��ü �޸� ���� destroy");
	}

}
