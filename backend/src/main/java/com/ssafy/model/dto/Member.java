package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Builder(toBuilder=true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter()
@Getter
public class Member {
	
	@Id
	@GeneratedValue
	private int mem_pk;			// PK
	
	@Column(length=20,nullable=false, unique=true)
	private String mem_id;		// 아이디
	
	@Column(length=40, nullable=false)
	private String mem_email;	// 이메일
	
	@Column(length=100, nullable=false)
	private String mem_pw;		// 비밀번호
	
	@Column(length=20, nullable=false)
	private String mem_nick;	// 닉네임

	@Column(length=20, nullable=false)
	private String mem_phone;	// 전화번호
	
	@Column(length=20, nullable=false)
	private String mem_birth;  	// 생년월일
	
	@Column(nullable=false, columnDefinition="TINYINT(1)")
	private Boolean mem_gender; 	// 성별	
}
