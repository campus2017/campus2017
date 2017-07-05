package com.qunar.fengxiaodong.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.qunar.fengxiaodong.model.Result;

@Service
public interface CountService {
	
	
	public Result statisticCharacterCountByFile(MultipartFile file);
	public Result statisticCharacterCountByString(String text);


	
	

}
