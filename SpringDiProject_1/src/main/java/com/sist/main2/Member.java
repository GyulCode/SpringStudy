package com.sist.main2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
	private String id, name, address, phone, sex;
	
	//개발자가 호출시킴
	public void print() {
		System.out.println("ID : "+id);
		System.out.println("Name : "+name);
		System.out.println("AD : "+address);
		System.out.println("Phoe : "+phone);
		System.out.println("Sex : "+sex);
	}
	
	
}
