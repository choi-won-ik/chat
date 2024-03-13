package com.example.demo.service.member;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.member.Member;
import com.example.demo.Entity.member.Profile;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.repository.member.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public String addProfile(Profile profile, MultipartFile img,String me,String absolutePath) throws Exception{
		// 이미지이름
		String imgName=img.getOriginalFilename();
		
		File file = new File(absolutePath);
		
		if(!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
		
		// 'file'객체를 통해 이미지파일 생성
		Path savePath = Paths.get(absolutePath,imgName);
		// 해당경로에 이미지파일 저장
		img.transferTo(savePath);
		// 아미지파일을 byte로 정의
		byte[] data = img.getBytes();
		
		log.info(data);
		
		Profile picture = Profile.builder()
				.userid(me)
				.fileName(imgName)
				.data(data)
				.build();
		
		Member member = memberRepository.findMemberByUserid(me);
		
		if(member.getProfile()==0) {
			profileRepository.save(picture);
			
			// 유저정보에서 프로필 있음으로 변경
			member.setProfile(1);
			memberRepository.save(member);
		}else {
			updataProfile(profileRepository.findByUserid(me),data,imgName);
		}
		
		return picture.getFileName();
	}
	
	@Transactional
	private void updataProfile(Profile profile,byte[] data,String imgName) {
		profile.setData(data);
		profile.setFileName(imgName);
		
		profileRepository.save(profile);
	}

	public List<Profile> findProfiles() {
		return profileRepository.findAll();
	}

	public byte[] findProfile(String me) {
		return profileRepository.findDataByUserid(me);
	}

}