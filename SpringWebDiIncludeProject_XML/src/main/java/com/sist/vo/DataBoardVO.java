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
	private List<MultipartFile> files; //���ε�� ���� ����, �������� list
	/*
	 * <input type=file name="files[0]"> -> �̷��������� �޾ƾ��� ������ Ʋ���� badrequest��
	 */
	

}
