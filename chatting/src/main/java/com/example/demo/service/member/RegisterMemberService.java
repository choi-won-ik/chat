package com.example.demo.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.member.Member;
import com.example.demo.repository.member.MemberRepository;

@Service
public class RegisterMemberService {
	private final MemberRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	public Long join(String userid, String pw) {
		Member member = Member.createUser(userid, pw, passwordEncoder,0);
		validateDuplicateMember(member);
		repository.save(member);

		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		repository.findByUserid(member.getUserid())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}
}
