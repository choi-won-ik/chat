package com.example.demo.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.member.Member;
import com.example.demo.repository.member.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RegisterMemberService {
	private final MemberRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	public Long join(String userid, String pw) {
		// 회원가입 용 builder
		Member member = Member.builder()
			    .userid(userid)
			    .pw(passwordEncoder.encode(pw))
			    .profile(0)
			    .build();
		
		validateDuplicateMember(member);
		repository.save(member);

		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		repository.findByUserid(member.getUserid())
				.ifPresent(m -> {
					log.info("여기서 실수");
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}
}
