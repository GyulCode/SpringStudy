package com.sist.dao;


/*
 * AOP�������� ���´�
 */
public class MyDAO {
	public void getConnection() {
		System.out.println("����Ŭ ����");
	}
	public void disConnection() {
		System.out.println("����Ŭ ����");
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
