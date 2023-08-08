package com.sist.dao;


/*
 * AOP공통으로 묶는다
 */
public class MyDAO {
	public void getConnection() {
		System.out.println("오라클 연결");
	}
	public void disConnection() {
		System.out.println("오라클 해제");
	}
	
	public void aopSelect() {
		getConnection();
		System.out.println("SELECT");
		disConnection();
	}
	public void aopInsert() {
		System.out.println("INSERT");
		disConnection();
	}
	public void aopUpdate() {
		System.out.println("Update");
	}
	public void aopDelete() {
		System.out.println("Delete");
	}

}
