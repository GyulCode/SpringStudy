package com.sist.vo;

/*
 * 1. ������ ���
 * 2. cookie
 * 3. session
 * 4. �α��� -> password ��ȣȭ / ��ȣȭ
 * 5. Front ->  vue�� �ۼ� -> filter����� ����� watch / computed / component / filter
 * 6. ȸ������ -> ��ȿ�� �˻�
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private int no, discount, hit, account;
	private String name, sub, price, first_price, delivery, poster;

}
