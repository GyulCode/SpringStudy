package com.sist.vo;

/*
 * 1. 페이지 기법
 * 2. cookie
 * 3. session
 * 4. 로그인 -> password 암호화 / 복호화
 * 5. Front ->  vue로 작성 -> filter기능을 배워봄 watch / computed / component / filter
 * 6. 회원가입 -> 유효성 검사
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private int no, discount, hit, account;
	private String name, sub, price, first_price, delivery, poster;

}
