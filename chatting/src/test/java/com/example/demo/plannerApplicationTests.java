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
		LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("현재 날짜와 시간: " + currentDateTime);
        int time = currentDateTime.hashCode();
        System.out.println(time);
	}

}
