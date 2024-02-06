package com.example.demo.Entity.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 11)
    private String userid;

    @Column(nullable = false, length = 100)
    private String pw;

    public static Member createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
        return new Member(null, userId, passwordEncoder.encode(pw));
    }
}
