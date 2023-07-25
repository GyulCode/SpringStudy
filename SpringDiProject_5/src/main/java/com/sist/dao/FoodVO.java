package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 * 이름                                      널?      유형
 ----------------------------------------- -------- ----------------------------
 CNO                                       NOT NULL NUMBER(2)
 TITLE                                     NOT NULL VARCHAR2(100)
 SUBJECT                                   NOT NULL VARCHAR2(200)
 POSTER                                    NOT NULL VARCHAR2(260)
 LINK                                      NOT NULL VARCHAR2(100)
 */
@Getter
@Setter
public class FoodVO {
	private int cno;
	private String title, subject, poster, link;

}
