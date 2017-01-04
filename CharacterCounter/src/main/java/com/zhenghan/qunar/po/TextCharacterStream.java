package com.zhenghan.qunar.po;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.zhenghan.qunar.util.ParserEncoding;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Author: 郑含
 * Date: 2016/12/15
 * Time: 17:17
 */
public class TextCharacterStream extends CharacterStream{
    public TextCharacterStream(InputStream stream) {
        super(stream);
    }
    @Override
    public String doStreamsToStrs(InputStream stream) throws IOException {
        byte[] buffer = ByteStreams.toByteArray(stream);
        Charset charset = ParserEncoding.INSTANCE.detectorEncoding(buffer);
        return new String(buffer, charset);
    }
}
