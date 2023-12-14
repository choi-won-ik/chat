package com.example.demo.repository.member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserid(String userId);

	List<Member> findByUseridStartingWith(String userid);
	
//	Long findIdByUserid(String SomeoneName);s
	@Query(
			value= "SELECT id from member u WHERE userid = :SomeoneName",
			nativeQuery  = true
			)
	Long findIdByUserid(@Param("SomeoneName") String SomeoneName);
}
