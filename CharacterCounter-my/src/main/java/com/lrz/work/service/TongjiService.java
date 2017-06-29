package com.lrz.work.service;

import com.lrz.work.pojo.TongjiResult;
import org.springframework.web.multipart.MultipartFile;

public interface TongjiService {

	public TongjiResult tongji(String content);
	
	public TongjiResult tongji(MultipartFile file) throws Exception ;
}
