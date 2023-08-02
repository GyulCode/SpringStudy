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
	
	//������
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
	
	//������ �߰�
	@GetMapping("databoard/insert.do")
	public String databoard_insert(Model model) {
		model.addAttribute("main_jsp","../databoard/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list= vo.getFiles();
		if(list==null) { //������ ���� ���
			vo.setFilename("");
			vo.setFilesize("");
			vo.setFilecount(0);
		}else {
			String filenames="",filesizes="";
			
			for(MultipartFile mf:list) {
				File file=new File("c:\\download\\"+mf.getOriginalFilename());  //systemFileName
				try {
					mf.transferTo(file);//���� ���ε�� ���
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
	
	//�󼼺���
	//��� �����ʹ� String���� ���� �� ���� int�� �����ϳ� ���ܰ� ����
	//�Ű������� ����x, �������� ��ٰ���(vo, map~
	/*
	 * MVC
	 * forward -> ȭ�� ���� -> request,response ���� �״�� ������
	 * sendredirect : ȭ�� ����(request�� �ʱ�ȭ������)
	 * 
	 * �Ű����� : ����ڰ� ������ �� �Ű������� �̸��� �Է¹޾ƿ� ���ε� �ڷ����� �̸��� ���ƾ��ϳ�.
	 * 1. �Ϲ� ������
	 * 2. vo(Ŀ�ǵ� ��ü)
	 * 3. checkbox -> String[]
	 * 
	 * ���尴ü
	 * [httpServletRequest, httpServletResponse ] -> �ٿ�ε�, cookie ��� 
	 * httpSession, Model, RedirectAtrributes, PasswordEncoder
	 * 
	 * DAO : DB����
	 * Controller : ������(DAO����, ����������, ������ ����)
	 * VO : ����� ���� ������(���� ������ �� ��Ƽ� ����)
	 * Manager : OpenAPI
	 * RestController : JavaScript�� JSON�� ������ ���� / �ٸ� ���� ����, �����͸� ������ �������� ����Ѵ�.
	 * -> ���� ���Ǵ°� 
	 * 1. WEB -> JavaScript(Ajax,VueJS,React)
	 * 2. Mobile -> Kotlin
	 * Service : BI -> DAO������ ����, �ٸ� ���� ����(ũ�Ѹ�) -> �������� ���� ���α׷�
	 * 
	 * 
	 */
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model) {
		DataBoardVO vo= dao.databoardDetailData(no);
		//���� �̸�(������) �̷������� ���
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
			File file=new File("c:\\download\\"+fn); //���� ���� ������ ���
			response.setHeader("Content-Disposition", "attachement; filename="
					+URLEncoder.encode(fn,"UTF-8") ); //attachement : ���� ������ ����ϴ� �Ӽ�

			//Download
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			// �������� ���� �б�
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			// ����ڿ��� ����
			
			int i=0; //���� byte ��
			byte[] buffer=new byte[1024]; //TCP(1024) , UDP(512)
			while((i=bis.read(buffer,0,1024))!=-1) { // -1 = EOF ������ ��
				bos.write(buffer,0,i);
			}
			
			bis.close();
			bos.close();
			
		} catch (Exception e) {}
	}
	@PostMapping("databoard/find.do")
	public String databoard_find(String fs, String ss, Model model) {
		
		//DAO ����
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
	@ResponseBody()// �°�(@restcontroller), �Ϲݵ����͸� ������ �ַ� ���(script...) 
	public String databoard_update_ok(DataBoardVO vo) {
		String result="";
		//DAO
		boolean bCheck=dao.databoardUpdate(vo);
		if(bCheck==true) {
			result="<script> location.href=\"../databoard/detail.do?no="+vo.getNo()+"\";"
					+ "</script>";
			
		}else {
			result="<script> alert(\"��й�ȣ�� Ʋ���ϴ�!!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result; 
	}
	
	//�����ϴ� â���� �̵�
	//databoard/delete.do?no=${vo.no}
	@GetMapping("databoard/delete.do")
	public String databoard_delete(int no, Model model) {
		model.addAttribute("no",no);
		model.addAttribute("main_jsp","../databoard/delete.jsp");
		return "main/main";
	}
	
	//���� ���� @ResponseBody() ��뿹��
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
			result="<script> alert(\"��й�ȣ�� Ʋ���ϴ�!!\");"
					+ "history.back()"
					+ "</script>";
		}
		return result;
	}
	
	
	
	
	

}
