package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.sist.dao.*;

/*
 * @controller
 * 요청처리 -> 요청한 결과값을 전송 -> 페이지 작성
 * jsp(forward) = request 전송 / .do (sendRedirect) = request를 전송
 *                                                   기존의 파일로 이동(이동하는 화면이 다른 경우)
 *   detail.do -> detail.jsp                                         isnert_ok.do -> list.do
 *                                                   update_ok.do -> detail.do
 * 
 * @RestController
 * 요청 처리 -> 요청한 결과값을 전송 -> 결과값만 전송, JS, 문자열, *JSON(Vue,React)
 * 
 * 
 */


@RestController
@RequestMapping("board/")
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	
	// ModelAttribute
	/*
	 * Spring
	 * 	Model(Controller) 메소드
	 * = 리턴형
	 * 		String / void
	 * = 매개변수
	 * 		단일변수(int, String ...)
	 * 		VO 객체
	 * 		List
	 * 		[]
	 * 		
	 * 		받는건 디스패쳐 서블릿
	 * 		-> Model(전송 객체 : forward), RedirectAttribute(전송 객체 : sendRedirect), HttpSession
	 * 		가장큰 특징은 request를 안쓴다.
	 * 		1) request.getParameter() -> 이걸 스프링에서 해줌 (DispatcherServlet에서 request.getPara~한 다음에 매개변수로 값을 전송해 준다.)
	 * 		2) request대신에 Model을 이용해서 request에 담긴 데이터를 JSP전송
	 * 			
	 */
	
	//이방식은 크롬에서만 가능 / JS데이터를 전송할 경우에 사용
	//Ajax, Vue, React -> JSON
	//Ajax, Vue, React -> BoardVO(JSON Object) List(JSON Array)
	@PostMapping(value = "update_ok.do",produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bcheck=dao.boardUpdate(vo);
		if(bcheck==true) {
			result="<script>location.href=\"detail.do?no="+vo.getNo()+"\"</script>";
		}
		else {
			result="<script> alert(\"비밀번호가 틀립니다\"); history.back(); </script>";
		}
		return result;
	}
	
	
	
}
