package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyVO {
	private int com_id, com_star_sum, com_star_cnt;
	private String com_name, address, phone, time, content, road_address, poster, hompage;
}
