package com.sist.dao;

public class BoardDAO {
	
	public String find(String name) {
		System.out.println("Find 荐青");
		return name;
	}
	public void aopSelect(String name) {
		System.out.println(name+"SELECT 荐青"); 
	}
	public void aopInsert() {
		System.out.println("insert 荐青"); 
	}
	public void aopUpdate() {
		System.out.println("update 荐青"); 
	}
	public void aopDelete() {
		System.out.println("delete 荐青"); 
	}
	

}
