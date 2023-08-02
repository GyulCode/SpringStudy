package com.sist.vo;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBoardVO {
	private int no, hit, filecount;
	private String name, subject, content, pwd, filename, filesize, dbday;
	private List<MultipartFile> files; //업로드된 파일 저장, 여러개는 list
	/*
	 * <input type=file name="files[0]"> -> 이런형식으로 받아야함 형식이 틀리면 badrequest임
	 */
	

}
