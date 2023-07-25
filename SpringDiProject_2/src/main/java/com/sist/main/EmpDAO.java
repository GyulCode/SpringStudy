package com.sist.main;

import java.util.*;

import lombok.Setter;

import java.sql.*;
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String driver;

	@Setter
	private String username;
	
	@Setter
	private String url; 
	
	@Setter
	private String password;
	//Setter�� �ʿ� ��� �������� ���� ���� �ִ�.
	
	//��ü�� setter�� ��ü�� ���� �־�� ������ ����� �����ϱ� ������ ��ü �������� ���ش�. 
	public EmpDAO(String driver) {
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
		}
	}
	
	public void disConnection()
	{
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO>list=new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql="SELECT empno, ename, job, hiredate, sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	
	
	// properties
	/*
	 * �������� Ŭ���� ��� -> �������� ����
	 * 1. ��ü ����
	 * 2. ��ü�� ��������� ���� �ʱⰪ
	 * 3. ��ü ����
	 * ----------- DI ū�������δ� �����ֱ��� ��ü�� ��Ÿ��, ��� �������� �Ǵ�
	 * �Ϲ� �������
	 * ��ü �ּ�(ref)
	 * 
	 * <bean id="b" calss="B" p:a-ref="a">
	 * <bean id="a" calss="A">
	 * ���� �ڵ���
	 *  A a=new A()
	 *  B b=new B()
	 *  b.setA(a)
	 *  �� ����
	 * 
	 */

}
