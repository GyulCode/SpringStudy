package com.sist.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;



//다른 언어 연결 -> VueJS
// FoodVO list<foodvo> 이런걸 보내면 -> JS가 못받아줌 -> FoodVO를 {}으로 바꿔 줘야한다 [{}, {}, {},...] -> JSON으로 변경 후 전송
// 이러한 과정을 restcontroller가 해줌
@RestController
@CrossOrigin("http://localhost:3000") //이포트를 서버에서 허용하도록 설정
public class FoodRestController {

}
