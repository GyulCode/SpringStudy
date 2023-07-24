package com.sist.main;

//객체 주입 -> 자동 주입, xml 주입

import java.util.*;

import lombok.Setter;

import java.sql.*;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	// 주입 -> 스프링을 통해서 멤버변수의 초기화 XML
	//setXxx()를 통해서 값대입
	
	@Setter
	private String url;
	@Setter
	private String username;
	@Setter
	private String password;
	
	
	public GoodsDAO(String driver) {
		try {
			Class.forName(driver);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void disConnection(){
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public List<String> goodsNameList(){
		List<String> list=new ArrayList<String>();
		try {
			getConnection();
			String sql="SELECT goods_name FROM goods_all "
					+ "ORDER BY no ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disConnection();
		}
		
		return list;
	}
	
	
}
