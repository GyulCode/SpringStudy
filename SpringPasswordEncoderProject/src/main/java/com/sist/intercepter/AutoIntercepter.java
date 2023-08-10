package com.sist.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/*
 *  						 	 인터셉트(preHandle)	   인터셉트(postHandle)	  인터셉트(afterCompletion)   
 *  요청(.do) -> dispatcherServlet ----> handlermapping	 ----> 	ViewResolver 	-----> 		JSP
 *  								@Controller/@RestController	 (return값을 받아서 화면 변경)
 *  								-> @GetMapping/@PostMapping
 *  Spring
 *  	setting : AOP/DI -> 클래스 들록
 *  	ORM(MyBatis) -> 우대(JPA)
 *  		1) XML
 *  		2) Annotation
 *  		3) XML+Annotation(중요***)
 *  	Transaction
 *  	----------입문
 *  	WebSocket
 *  	Security (권한부여(시간상 못함), 패스워드 암복호화)
 *  	Task
 *  	----------옵션
 *  	MVC
 *  	
 *  	강사님 참고...
 *  
 */
public class AutoIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AutoIntercepter call...");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle call...");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion call...");
		super.afterCompletion(request, response, handler, ex);
	}
	

}
