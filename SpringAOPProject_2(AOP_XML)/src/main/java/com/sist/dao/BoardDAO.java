package com.sist.dao;

public class BoardDAO {
	
	public String find(String name) {
		System.out.println("Find ����");
		return name;
	}
	public void aopSelect(String name) {
		System.out.println(name+"SELECT ����"); 
	}
	public void aopInsert() {
		System.out.println("insert ����"); 
	}
	public void aopUpdate() {
		System.out.println("update ����"); 
	}
	public void aopDelete() {
		System.out.println("delete ����"); 
	}
	

}
