package com.sist.main2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Member {
	private String id, name, sex;
	
	public void init() {
		System.out.println("========= ��� ���� ==========");
		
	}
	public void print() {
		System.out.println("���̵�:"+id);
		System.out.println("�̸�:"+name);
		System.out.println("����:"+sex);
	}

}
