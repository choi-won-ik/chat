package com.example.demo.service.member;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.member.Member;
import com.example.demo.repository.member.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProfileService {

	@Autowired
	private MemberRepository memberRepository;

	public void addProfile(MultipartFile img,String me,String absolutePath) throws Exception{
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
		// 파일 확장자 추츨
		String extensions = FilenameUtils.getExtension(imgName);
		
//		Profile picture = Profile.builder()
//				.userid(me)
//				.fileName(imgName)
//				.data(data)
//				.Extensions(extensions)
//				.build();
		
		Member member = memberRepository.findMemberByUserid(me);
		
		member.setProfile(1);
		member.setData(data);
		member.setExtensions(extensions);
		
		memberRepository.save(member);
		
//		return picture.getFileName();
	}
	
//	@Transactional
//	private void updataProfile(Profile profile,byte[] data,String imgName,String extensions) {
//		// 파일 데이터 업데이트
//		profile.setData(data);
//		// 파일 이름 업데이트
//		profile.setFileName(imgName);
//		// 파일 확장자 업데이트
//		profile.setExtensions(extensions);
//		
//		profileRepository.save(profile);
//	}
}