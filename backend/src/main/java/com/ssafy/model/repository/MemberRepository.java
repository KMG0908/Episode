package com.ssafy.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.model.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	@Query(value = "SELECT * FROM MEMBER m WHERE m.mem_id = ?1", nativeQuery=true)
	Member findByMemId(String mem_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE mydbinstance.member m SET m.name=:#{#member.name} WHERE m.mem_pk=:mem_pk", nativeQuery = true)
	void update(@Param("member") Member member, @Param("mem_pk") int mem_pk);
}
