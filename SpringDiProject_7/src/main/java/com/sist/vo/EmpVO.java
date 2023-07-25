package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO {
	/* 가급적이면 DB와 이름 동일 */
	private int empno, sal, deptno, comm;
	private String ename, job, dbday, dbsal;
	private Date hiredate;

}
