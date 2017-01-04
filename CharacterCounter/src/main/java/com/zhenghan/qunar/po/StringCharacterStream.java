package com.zhenghan.qunar.po;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.zhenghan.qunar.util.ParserEncoding;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: 郑含
 * Date: 2016/12/15
 * Time: 17:18
 */
public class StringCharacterStream extends CharacterStream {
    public StringCharacterStream(InputStream stream) {
        super(stream);
    }

    public StringCharacterStream(String datas) {
        super(datas);
    }
    public String doStreamsToStrs(InputStream stream) throws IOException {
        Preconditions.checkNotNull(stream);
        byte[] buffer = ByteStreams.toByteArray(stream);
        return new String(buffer,ParserEncoding.INSTANCE.detectorEncoding(buffer));
    }
}
