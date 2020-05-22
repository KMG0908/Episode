package com.ssafy.model.dto.member;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Auth {	// 로그인 관련 클래스
	String memId;		// 아이디
	String memPw;		// 비밀번호
}
