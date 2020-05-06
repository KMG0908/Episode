package com.ssafy.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Auth {	// 로그인 관련 클래스
	private String mem_id;		// 아이디
	private String mem_pw;		// 비밀번호
}
