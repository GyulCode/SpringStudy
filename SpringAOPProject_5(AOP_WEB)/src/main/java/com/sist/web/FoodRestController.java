package com.sist.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



//�ٸ� ��� ���� -> VueJS
// FoodVO list<foodvo> �̷��� ������ -> JS�� ���޾��� -> FoodVO�� {}���� �ٲ� ����Ѵ� [{}, {}, {},...] -> JSON���� ���� �� ����
// �̷��� ������ restcontroller�� ����
@RestController
@CrossOrigin("http://localhost:3000") //����Ʈ�� �������� ����ϵ��� ����
public class FoodRestController {

}
