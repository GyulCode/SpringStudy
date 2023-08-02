package com.sist.main;

import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ClassPathApplicationContext implements ApplicationContext{
	private Map clsMap=new HashMap();
    public ClassPathApplicationContext(String path) {
		//파싱된 내용을 기록
		try {
			
			SAXParserFactory spf=SAXParserFactory.newInstance();
    		SAXParser sp=spf.newSAXParser();
    		XMLParse xm=new XMLParse(); // 스타트 엘리먼트를 읽을때 이형식으로 읽어라 지정형식 우리가 생성한 java파일
    		sp.parse(new File(path), xm); //기본 핸들러 대신 재정의한 xm핸들러 사용 // XMLParse
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
