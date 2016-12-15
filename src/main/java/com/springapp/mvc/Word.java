package com.springapp.mvc;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/14.
 */
@Data
public class Word {
    private String str;
    private int count;

    private int enCount;
    private int chCount;
    private int digitCount;
    private int punCount;
}
