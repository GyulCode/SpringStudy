package com.sist.main3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private int hakbun, kor, eng, math;
	private String name;
	
	public void print()
	{
		System.out.println("�й�: "+hakbun);
		System.out.println("�̸�: "+name);
		System.out.println("����: "+kor);
		System.out.println("����: "+eng);
		System.out.println("����: "+math);
		System.out.println("Total: "+(math+eng+kor));
		System.out.printf("AVG: %.2f \n",(math+eng+kor)/3.0);
	}

}
