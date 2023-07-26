package com.sist.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface StudentMapper {
	@Select("SELECT * FROM student")
	public List<StudentVO> studentListData();
	
	
	@Select("SELECT * FROM student WHERE hakbun=#{hakbun}")
	 public StudentVO studentDetailData(int hakbun);
	
	//검색
	@Select("SELECT * FROM Student WHERE name LIKE '%'||#{name}||'%'")
	public List<StudentVO> studentFindData(String name);
	
	//수정
	@Select("UPDATE student SET name=#{name}, kor=#{kor}, eng=#{eng}, math=#{math} WHERE hakbun=#{hakbun}")
	public void studentUpdate(StudentVO vo);
	
	//삭제
	@Select("DELETE FROM student WHERE hakbun=#{hakbun}")
	public void studentDelete(int hakbun);
	
	//삽입
	 @SelectKey(keyProperty="hakbun",resultType=int.class,before=true,
			  statement="SELECT NVL(MAX(hakbun)+1,1) as hakbun FROM student")
	 @Insert("INSERT INTO student VALUES(#{hakbun}, #{name},#{kor},#{eng},#{math})")
	 public void studentInsert(StudentVO vo);

}
