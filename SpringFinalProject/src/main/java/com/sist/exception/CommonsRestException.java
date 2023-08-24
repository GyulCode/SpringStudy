package com.sist.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsRestException {
	   @ExceptionHandler(RuntimeException.class)
	   public void runtimeException(RuntimeException ex)
	   {
		   System.out.println("========= RuntimeException ¹ß»ý =======");
		   ex.printStackTrace();
		   System.out.println("======================================");
	   }
}