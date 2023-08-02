package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * ��û : .do
 * DispatcherServlet
 * 	-> HandlerAdapter : Model Ŭ���� ã��
 * 	-> HandlerMapping : @getMapping, @PostMapping, @RequestMapping... [�޼ҵ� ã�� -> ȣ��]
 * 
 * DispatcherServlet
 *  -> request -> ViewResolver(JSP)
 * 
 */
//���������� Ŭ���� ���
@Configuration
//<context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = {"com.sist.*"})
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = {"com.sist.mapper"})
public class SpringConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); //HandlerMapping / HandlerAdapter �޸� �Ҵ�
	}
	/*
	 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	  p:dataSource-ref="ds" 
	/>
	 */
	
	
	/*
	 * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	 p:prefix="/"
	 p:suffix=".jsp"
	/>
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver iv=new InternalResourceViewResolver();
		iv.setPrefix("/");
		iv.setSuffix(".jsp");
		return iv;
	}
	
	
	/*
	 * <bean id="multipartResolver"
	  class="org.springframework.web.multipart.commons.CommonsMultipartResolver"
	/>
	 */
	@Bean("mulitpartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cr=new CommonsMultipartResolver();
		return cr;
	}
	
	
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		ds.setMaxWait(-1);
		return ds;
	}
	
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}

}
