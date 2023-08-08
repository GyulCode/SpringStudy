package com.sist.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.dao.EmpVO;

/*
 * 1.before
 * 2.after
 * --------
 * 3.around
 * 4.after-returning
 * 5.after-throwing
 * 
 * AOP 메뉴같은거 볼때 로그인 필요하다~ 이런거 출력시 사용
 * 
 */

public class EmpAspect {
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("Around call...try시작, try종료");
		/*
		try {
			-----------여기서 처리
			핵심
			-----------여기서 처리
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		Object obj=null;
		long start=0, end=0;
		
		start=System.currentTimeMillis();//시작점
		System.out.println("사용자가 호출한 메소드명 : "+jp.getSignature().getName());
		obj=jp.proceed(); //메소드 호출 -> invoke()
		
		end=System.currentTimeMillis();//종료점
		System.out.println("수행 시간 : "+(end-start));
		
		
		return obj;
	}
	
	//정상 수행후 리턴값을 받아서 처리
	public void afterReturning(Object obj) throws Throwable
	{
		System.out.println("afterReturning Call.. 정상수행");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getDbday()+" ");
		}
	}
	
	//catch절 수행시
	public void afterThrowing(Throwable ex) throws Throwable{
		System.out.println("afterThrowing Call...");
		ex.printStackTrace();
		
	}
}
