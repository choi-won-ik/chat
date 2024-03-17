package com.example.demo.Entity.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 11)
    private String userid;

    @Column(nullable = false)
    private String pw;
    
	@Column(nullable = false, length = 1)
	private int profile;
	
	@Lob
	@Column(length = 9999999)
	private byte[] data;
	
	@Column(length = 5)
	private String Extensions;
    
    public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder,int profile) {
        return new Member(null, userId, passwordEncoder.encode(pw),profile,null,null);
    }
}
