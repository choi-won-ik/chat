package com.example.demo.config.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers( "/img/**", "/view/join", "/auth/join").permitAll()
					.anyRequest().authenticated()						// 어떠한 요청이라도 인증필요
					)
			
			.formLogin(login -> login									// form 방식 로그인 사용
					.loginPage("/view/login")							// custom 로그인페이지
					.loginProcessingUrl("/login-process")				// submit을 받을 url
					.usernameParameter("userid")						// submit할 아이디
					.passwordParameter("pw")							// submit할 비밀번호
					.defaultSuccessUrl("/view/dashboard", true)			// 성공 시 '/view/dashboard'로 이동
					.failureUrl("/view/loginError")						// 로그인 실패 시
					.permitAll()
					)
			.logout(withDefaults());									// 로그아웃은 기본설정으로 (/logout으로 인증해제)

		return http.build();
	}
}