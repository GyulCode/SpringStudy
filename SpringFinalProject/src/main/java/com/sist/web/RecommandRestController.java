package com.sist.web;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
@RestController
public class RecommandRestController {
	private String[] type1= {"��", "����", "����", "�ܿ�", "������", "�߿", "�帰��" ,"����³�", "���","�����³�"};
	private String[] type2= {"�ܷο� ��", "�����ȯ", "���� ��", "�̺��ϴ� ��" ,"��ĥ�� ��", "���� �ް� ���� ��" ,"¥������ ��",  "����� ��",
	 "�ູ�� ��", "�Ҿ��� ��" , "��� ��", "���Ϲް� ���� ��"};
	private String[] type3= {"���� ��", "�ų��� ��", "������ ��", "����� ��", "������ ��", "�ÿ��� ��" ,  "��ο� ��"};
	
	@GetMapping(value="recommand/recommand_sub_vue.do",produces = "text/plain;charset=UTF-8")
	public String recommand_sub(int no) throws Exception
	{
		List<String> list=new ArrayList<String>();
		if(no==1)
		{
			list=Arrays.asList(type1);
		}
		else if(no==2)
		{
			list=Arrays.asList(type2);
		}
		else if(no==3)
		{
			list=Arrays.asList(type3);
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}