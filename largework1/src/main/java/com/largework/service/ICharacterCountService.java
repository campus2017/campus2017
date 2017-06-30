package com.largework.service;

import com.largework.model.Count;
import com.largework.model.UploadFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liudan on 2017/6/16.
 */
public interface ICharacterCountService {
    public Count characterCount(String text);
    public UploadFile uploadFile(HttpServletRequest request);
    public String jsonReturn(Count count);
}
