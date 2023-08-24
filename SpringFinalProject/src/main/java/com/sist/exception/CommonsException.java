package com.sist.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("=========RuntimeException 惯积=========");
		ex.printStackTrace();
		System.out.println("======================================");
	}
	@ExceptionHandler(IOException.class)
	public void ioexception(IOException ex) {
		System.out.println("=========IOException 惯积=========");
		ex.printStackTrace();
		System.out.println("======================================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlexception(SQLException ex) {
		System.out.println("=========SQLException 惯积=========");
		ex.printStackTrace();
		System.out.println("======================================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex) {
		System.out.println("=========Exception 惯积=========");
		ex.printStackTrace();
		System.out.println("======================================");
	}
	// IOException, Sqlexception, Exception
	

}
