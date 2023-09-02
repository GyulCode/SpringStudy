package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="seoul_location")
@Getter
@Setter
public class SeoulLocationEntity {
	
	@Id
	public int no; 
	public int hit;
	public String title, poster, msg, address;

}
