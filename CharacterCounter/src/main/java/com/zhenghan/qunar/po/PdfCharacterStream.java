package com.zhenghan.qunar.po;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

/**
 * Author: 郑含
 * Date: 2017/1/18
 * Time: 22:32
 */
public class PdfCharacterStream extends CharacterStream{
    public PdfCharacterStream(InputStream stream) {
        super(stream);
    }
    @Override
    public String doStreamsToStrs(InputStream stream) throws IOException {
          return doPdf(stream);
    }
    private String doPdf(InputStream stream) throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(1024);
        Writer writer = new OutputStreamWriter(arrayOutputStream,"utf-8");
        PDDocument pdDocument = null;
        try {
            pdDocument = PDDocument.load(stream);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            stripper.setStartPage(0);
            stripper.setEndPage(Integer.MAX_VALUE);
            stripper.writeText(pdDocument, writer);
            writer.flush();
            byte[] buffer =arrayOutputStream.toByteArray();
            return new String(buffer,"utf-8");
        }finally {
            if(pdDocument != null)
                pdDocument.close();
        }
    }

}
