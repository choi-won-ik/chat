package com.example.demo.repository.member;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserid(String userId);

	List<Member> findByUseridStartingWith(String userid);
}
