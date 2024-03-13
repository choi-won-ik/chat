package com.example.demo.service.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.member.Member;
import com.example.demo.repository.member.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.memberRepository = repository;
    }

    public Optional<Member> findOne(String userId) {
        return memberRepository.findByUserid(userId);
    }

    public boolean isValidMember(String userId, String password) {
        Optional<Member> member = findOne(userId);
        if (member.isPresent()) {
            return member.get().getPw().equals(password);
        }
        return false;
    }
    
    // 유정명으로 고유 id찾기
    public Long number(String me) {
		return memberRepository.findIdByUserid(me);
	}
}
