package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodVO {
	private int fno,cno;
	private String name,address,tel,type,price,parking,time,menu,poster;
	private double score;
	private String sessionId;

}
