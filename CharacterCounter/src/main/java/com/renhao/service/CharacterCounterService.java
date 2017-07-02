package com.renhao.service;

import com.renhao.model.CountResult;
import org.springframework.web.multipart.MultipartFile;


public interface CharacterCounterService {

    public CountResult getResultOfText(String text);

    public CountResult getResultOfFile(MultipartFile file);
}
