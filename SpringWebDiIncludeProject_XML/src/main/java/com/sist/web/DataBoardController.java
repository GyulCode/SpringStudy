package com.sist.web;

import org.apache.tiles.template.AddAttributeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dao;
	
	//목록출력
	@GetMapping("databoard/list.do")
	public String databoard_list(String page, Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=dao.databoardListData(map);
		int totalpage=dao.databoardTotalPage();
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("main_jsp","../databoard/list.jsp");
		return "main/main";
	}
	
	//데이터 추가
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model) {
		model.addAttribute("main_jsp","../databoard/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list= vo.getFiles();
		if(list==null) { //파일이 없는 경우
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}else {
			String filenames="",filesizes="";
			
			for(MultipartFile mf:list) {
				File file=new File("c:\\download\\"+mf.getOriginalFilename());  //systemFileName
				try {
					mf.transferTo(file);//파일 업로드시 사용
				} catch (Exception e) {}
				
				filenames+=file.getName()+",";
				long len=file.length();
				filesizes+=len+",";
				
			}
			filenames=filenames.substring(0,filenames.lastIndexOf(","));
			filesizes=filesizes.substring(0,filesizes.lastIndexOf(","));
			vo.setFilename(filenames);
			vo.setFilesize(filesizes);
			vo.setFilecount(list.size());
		}
		
		dao.databoardInsert(vo);
		return "redirect:../databoard/list.do";
	}
	
	//상세보기
	//모든 데이터는 String으로 받을 수 있음 int도 가능하나 예외가 있음
	//매개변수는 순서x, 여러개는 당근가능(vo, map~
	/*
	 * MVC
	 * forward -> 화면 변경 -> request,response 같이 그대로 보내줌
	 * sendredirect : 화면 변경(request를 초기화시켜줌)
	 * 
	 * 매개변수 : 사용자가 보내준 값 매개변수의 이름과 입력받아온 매핑된 자료형의 이름이 같아야하낟.
	 * 1. 일반 데이터
	 * 2. vo(커맨드 객체)
	 * 3. checkbox -> String[]
	 * 
	 * 내장객체
	 * [httpServletRequest, httpServletResponse ] -> 다운로드, cookie 사용 
	 * httpSession, Model, RedirectAtrributes, PasswordEncoder
	 * 
	 * DAO : DB연동
	 * Controller : 조립기(DAO연동, 데이터추출, 브라우저 전송)
	 * VO : 사용자 정의 데이터(관련 데이터 를 모아서 관리)
	 * Manager : OpenAPI
	 * RestController : JavaScript로 JSON을 전송할 목적 / 다른 언어와 연결, 데이터를 전송할 목적으로 사용한다.
	 * -> 많이 사용되는게 
	 * 1. WEB -> JavaScript(Ajax,VueJS,React)
	 * 2. Mobile -> Kotlin
	 * Service : BI -> DAO여러개 통합, 다른 서비스 통합(크롤링) -> 의존성이 낮은 프로그램
	 * 
	 * 
	 */
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model) {
		DataBoardVO vo= dao.databoardDetailData(no);
		//파일 이름(사이즈) 이런형식의 출력
		if(vo.getFilecount()>0) {
			String filenames=vo.getFilename();
			StringTokenizer st=new StringTokenizer(filenames,",");
			List<String > nList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				nList.add(st.nextToken());
			}
			
			String filesizes=vo.getFilesize();
			st=new StringTokenizer(filesizes,",");
			List<String > sList=new ArrayList<String>();
			while(st.hasMoreTokens()) {
				sList.add(st.nextToken());
			}
			
			model.addAttribute("nList",nList);
			model.addAttribute("sList",sList);
		}
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../databoard/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/download.do")
	public void databoardDownload(String fn, HttpServletResponse response) {
		try {
			File file=new File("c:\\download\\"+fn); //파일 정보 읽을때 사용
			response.setHeader("Content-Disposition", "attachement; filename="
					+URLEncoder.encode(fn,"UTF-8") ); //attachement : 파일 보낼때 사용하는 속성

			//Download
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// 서버에서 파일 읽기
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			// 사용자에게 전송
			
			int i=0; //읽은 byte 수
			byte[] buffer=new byte[1024]; //TCP(1024) , UDP(512)
			while((i=bis.read(buffer,0,1024))!=-1) { // -1 = EOF 파일의 끝
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
			
		} catch (Exception e) {}
	}
	@PostMapping("databoard/find.do")
	public String databoard_find(String fs, String ss, Model model) {
		
		//DAO 연결
		Map map=new HashMap();
		map.put("fs",fs);
		map.put("ss",ss);
		List<DataBoardVO> list=dao.databoardFindData(map);
		model.addAttribute("list",list);
		model.addAttribute("count",list.size());
		model.addAttribute("main_jsp","../databoard/find.jsp");
		return "main/main";
	}
	
	@GetMapping("databoard/update.do")
	public String databoard_update(int no, Model model) {
		
		//DAO
		DataBoardVO vo =dao.databoardUpdateData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_jsp","../databoard/update.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/update_ok.do")
	@ResponseBody()// 승격(@restcontroller), 일반데이터를 보낼때 주로 사용(script...) 
	public String databoard_update_ok(DataBoardVO vo) {
		String result="";
		//DAO
		boolean bCheck=dao.databoardUpdate(vo);
		if(bCheck==true) {
			result="<script> location.href=\"../databoard/detail.do?no="+vo.getNo()+"\";"
					+ "</script>";
			
		}else {
			result="<script> alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result; 
	}
	
	//삭제하는 창으로 이동
	//databoard/delete.do?no=${vo.no}
	@GetMapping("databoard/delete.do")
	public String databoard_delete(int no, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/delete.jsp");
		return "main/main";
	}
	
	//실제 삭제 @ResponseBody() 사용예정
	@PostMapping("databoard/delete_ok.do")
	@ResponseBody
	public String databoard_delete_ok(int no, String pwd) {
		String result="";
		//DAO
		boolean bCheck=dao.databoardDelete(no, pwd);
		if(bCheck==true) {
			result="<script> location.href=\"../databoard/list.do\""
					+ "</script>";
			
		}else {
			result="<script> alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result;
	}
	
	
	
	
	

}
