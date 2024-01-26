package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class plannerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		String input = "10&20";

        // & 이전의 내용을 없애기
        int index = input.indexOf("&");
        if (index != -1) {
            String result = input.substring(index + 1);
            System.out.println(result);
        } else {
            System.out.println("문자열에 '&'이 없습니다.");
        }
	}

}
