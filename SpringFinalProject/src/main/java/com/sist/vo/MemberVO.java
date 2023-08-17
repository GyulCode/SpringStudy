package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberVO {
	private String id, pwd, name, sex, birthday, email, post, addr1, addr2, content, admin;
	private String phone, phone1, dbday, msg;
	private Date regdate;
	private String role;
	

}
