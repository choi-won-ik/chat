package com.example.demo.repository.member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.member.Member;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MemberRepository extends JpaRepository<Member, Long> {
	// 유저명 찾기
    Optional<Member> findByUserid(String userId);

    // 입력값으로 시작하는 유저명 불러옴
    CopyOnWriteArrayList<Member> findByUseridStartingWith(String userid);
	
	// 유저명으로 해당 id값 불러옴
	@Query(
			value= "SELECT id from member WHERE userid = :talkerName",
			nativeQuery  = true
			)
	Long findIdByUserid(@Param("talkerName") String talkerName);

	@Query(
			value= "SELECT userid from member WHERE id = :talkerName",
			nativeQuery  = true
			)
	String findUserById(@Param("talkerName") long talkerName);

	@Query(
			value= "SELECT * from member WHERE userid = :me",
			nativeQuery  = true
			)
	Member findMemberByUserid(@Param("me") String me);
	
}
