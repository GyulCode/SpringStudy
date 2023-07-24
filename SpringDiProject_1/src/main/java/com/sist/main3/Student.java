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
		System.out.println("학번: "+hakbun);
		System.out.println("이름: "+name);
		System.out.println("국어: "+kor);
		System.out.println("영어: "+eng);
		System.out.println("수학: "+math);
		System.out.println("Total: "+(math+eng+kor));
		System.out.printf("AVG: %.2f \n",(math+eng+kor)/3.0);
	}

}
