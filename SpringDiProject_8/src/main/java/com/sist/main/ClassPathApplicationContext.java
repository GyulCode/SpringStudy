package com.sist.main;

import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ClassPathApplicationContext implements ApplicationContext{
	private Map clsMap=new HashMap();
    public ClassPathApplicationContext(String path) {
		//�Ľ̵� ������ ���
		try {
			
			SAXParserFactory spf=SAXParserFactory.newInstance();
    		SAXParser sp=spf.newSAXParser();
    		XMLParse xm=new XMLParse(); // ��ŸƮ ������Ʈ�� ������ ���������� �о�� �������� �츮�� ������ java����
    		sp.parse(new File(path), xm); //�⺻ �ڵ鷯 ��� �������� xm�ڵ鷯 ��� // XMLParse
    		clsMap=xm.getMap();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public Object getBean(String key) {
		// TODO Auto-generated method stub
		return clsMap.get(key);
	}
	

}
