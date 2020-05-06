package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Auth;
import com.ssafy.model.dto.AuthException;
import com.ssafy.model.dto.Member;
import com.ssafy.model.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
//	@Autowired
//	MemberDao mDao;

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public Member login(Auth auth) {
//		Member member = mDao.getMember(auth.getMem_id());
		
		Member member = memberRepository.findByMemId(auth.getMem_id());
		if(member == null) {
			throw new AuthException("아이디가 존재하지 않음");						
		}else {
			if(member.getMem_pw().equals(auth.getMem_pw())) {
				return member;
			}
		throw new AuthException("비밀번호 틀림");
		}
	}

	@Override
	public void regist(Member member) {
//		Boolean result = mDao.registMember(member);
		Member rMember = memberRepository.save(member);
		System.out.println("member : " + member);
		System.out.println("rMember : " + rMember);
	}
	
	@Override
	public List<Member> getMembers(){
//		List<Member> members = mDao.getMembers();
//		return members;
		List<Member> members = memberRepository.findAll();
		return members;
	}
	
	
}
