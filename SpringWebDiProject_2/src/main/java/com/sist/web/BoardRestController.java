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
 * ��ûó�� -> ��û�� ������� ���� -> ������ �ۼ�
 * jsp(forward) = request ���� / .do (sendRedirect) = request�� ����
 *                                                   ������ ���Ϸ� �̵�(�̵��ϴ� ȭ���� �ٸ� ���)
 *   detail.do -> detail.jsp                                         isnert_ok.do -> list.do
 *                                                   update_ok.do -> detail.do
 * 
 * @RestController
 * ��û ó�� -> ��û�� ������� ���� -> ������� ����, JS, ���ڿ�, *JSON(Vue,React)
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
	 * 	Model(Controller) �޼ҵ�
	 * = ������
	 * 		String / void
	 * = �Ű�����
	 * 		���Ϻ���(int, String ...)
	 * 		VO ��ü
	 * 		List
	 * 		[]
	 * 		
	 * 		�޴°� ������ ����
	 * 		-> Model(���� ��ü : forward), RedirectAttribute(���� ��ü : sendRedirect), HttpSession
	 * 		����ū Ư¡�� request�� �Ⱦ���.
	 * 		1) request.getParameter() -> �̰� ���������� ���� (DispatcherServlet���� request.getPara~�� ������ �Ű������� ���� ������ �ش�.)
	 * 		2) request��ſ� Model�� �̿��ؼ� request�� ��� �����͸� JSP����
	 * 			
	 */
	
	//�̹���� ũ�ҿ����� ���� / JS�����͸� ������ ��쿡 ���
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
			result="<script> alert(\"��й�ȣ�� Ʋ���ϴ�\"); history.back(); </script>";
		}
		return result;
	}
	
	
	
}
