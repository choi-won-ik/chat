package com.example.demo.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.member.Profile;
import java.util.List;


public interface ProfileRepository extends JpaRepository<Profile, Long>{

	@Query(
			value= "SELECT data from profile WHERE userid = :me",
			nativeQuery  = true
			)
	byte[] findDataByUserid(@Param("me")String me);
	
	Profile findByUserid(String userid);
}