package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 스프링5 버전 코딩 xml파일도 java로 코딩할 수 있게하는게 목표 //app2 파일 코딩
@Configuration
@ComponentScan(basePackages = "com.sist.*") // <context:component-scan base-package="com.sist.*"/>
@MapperScan(basePackages = "com.sist.mapper2") // <mybatis-spring:scan base-package="com.sist.mapper2"/>
public class EmpConfig {
	// <bean id="ds"
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds= new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	
	/*
	 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
	 */
	
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}

}
