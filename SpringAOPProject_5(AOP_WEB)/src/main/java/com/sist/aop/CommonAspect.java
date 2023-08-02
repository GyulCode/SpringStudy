package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.service.*;

@Aspect
@RestControllerAdvice //Controller���� �߻��ϴ� ������ ��Ƽ� �갡 ó��
public class CommonAspect {
	@Autowired
	private FoodService service;
	
	@After("execution(* com.sist.web.*Controller.*(..))") //final�κ� footer�� ���� ä����
	public void footerDataSend() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<SeoulVO> seoulList=service.seoulTop7();
		List<FoodVO> foodList=service.foodTop7();
		
		request.setAttribute("seoulList", seoulList);
		request.setAttribute("foodList", foodList);
	}
	
	@Around("execution(* com.sist.web.*Controller.*(..))")
	public Object logData(ProceedingJoinPoint jp) throws Throwable{
		
		Object obj=null;
		long start =System.currentTimeMillis();
		System.out.println(jp.getSignature().getName()+":�޼ҵ忡 ����");
		obj=jp.proceed();
		System.out.println(jp.getSignature().getName()+":�޼ҵ� ���� ����");
		long end =System.currentTimeMillis();
		System.out.println("����ð� : "+(end-start));
		return obj;
	}
	

}
