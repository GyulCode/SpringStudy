package com.sist.web.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * 
FNO int 
NAME text 
SCORE double 
ADDRESS text 
TEL text 
TYPE text 
PRICE text 
TIME text 
PARKING text 
MENU text 
HIT int 
POSTER text
 */

@Getter
@Setter
public class FoodVO {
	public int fno, hit;
	public double score;
	public String name, addres, tel, type, price, time, parking, menu, poster;
}
