package com.sist.main;

//��ü ���� -> �ڵ� ����, xml ����

import java.util.*;

import lombok.Setter;

import java.sql.*;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	// ���� -> �������� ���ؼ� ��������� �ʱ�ȭ XML
	//setXxx()�� ���ؼ� ������
	
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
