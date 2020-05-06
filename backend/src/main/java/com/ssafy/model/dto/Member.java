package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mem_pk;
	@Column(name="mem_id", length = 100, nullable = false, unique=true)
	private String memId;
	@Column(name="mem_email", length = 200, nullable = false, unique=true)
	private String memEmail;
	@Column(name="mem_pw", length = 512, nullable = false)
	private String memPw;
	@Column(name="mem_nick", length = 100, nullable = false, unique=true)
	private String memNick;
	@Column(name="mem_phone", length = 20, nullable = false, unique=true)
	private String memPhone;
	@Column(name="mem_birth", length = 20, nullable = false)
	private String memBirth;
	@Column(name="mem_gender", nullable = false, columnDefinition="TINYINT(1)")
	private boolean memGender;
}
