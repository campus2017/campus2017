package com.youthlin.qunar.diff.service;

import com.google.common.base.Charsets;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.youthlin.qunar.diff.common.Constrant;
import com.youthlin.qunar.diff.dao.DifferentMapper;
import com.youthlin.qunar.diff.dao.UserMapper;
import com.youthlin.qunar.diff.model.Different;
import com.youthlin.qunar.diff.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * 操作 “差异” 实体
 */
@Service
public class DifferentService {
    private static final Logger log = LoggerFactory.getLogger(DifferentService.class);

    @Resource
    private DifferentMapper differentMapper;
    @Resource
    private UserMapper userMapper;

    public Different save(Different different) {
        differentMapper.save(different);
        return different;
    }

    public int delete(Integer did, Integer uid) {
        return differentMapper.delete(did, uid);
    }

    public Different findById(Integer did) {
        return differentMapper.findById(did);
    }

    public List<Different> findByUserId(Integer uid) {
        return differentMapper.findByUserId(uid);
    }

    /*page=1起始*/
    public List<Different> findByPageAndUserId(Integer uid, int page) {
        int start = (page - 1) * Constrant.itemPerPage;
        return differentMapper.findPageByUserId(uid, start, Constrant.itemPerPage);
    }

    public String saveDifferent(MultipartFile sourceFile, MultipartFile targetFile,
                                HttpSession session) throws IOException {
        File tempDir = Files.createTempDir();
        File source = new File(tempDir, System.currentTimeMillis() + sourceFile.getOriginalFilename());
        File target = new File(tempDir, System.currentTimeMillis() + targetFile.getOriginalFilename());
        sourceFile.transferTo(source);
        targetFile.transferTo(target);
        log.debug("文件已存在临时目录：{}", tempDir.getAbsolutePath());

        String errorMsg = processDiff(source, sourceFile.getOriginalFilename(),
                target, targetFile.getOriginalFilename(), session);

        boolean delete = source.delete();
        delete &= target.delete();
        delete &= tempDir.delete();
        log.debug("deleted temp files = {}", delete);

        return errorMsg;
    }

    private String processDiff(File source, String sourceOriginalFilename,
                               File target, String targetOriginalFilename,
                               HttpSession session) throws IOException {
        KeyValueLineProcessor keyValueLineProcessor = new KeyValueLineProcessor(sourceOriginalFilename);

        Files.readLines(source, Charsets.UTF_8, keyValueLineProcessor);
        Map<String, String> sourceResult = keyValueLineProcessor.getResult();
        String sourceError = keyValueLineProcessor.getError();
        String sourceContent = keyValueLineProcessor.getContent();

        keyValueLineProcessor.reset(targetOriginalFilename);

        Files.readLines(target, Charsets.UTF_8, keyValueLineProcessor);
        Map<String, String> targetResult = keyValueLineProcessor.getResult();
        String targetError = keyValueLineProcessor.getError();
        String targetContent = keyValueLineProcessor.getContent();

        log.debug("result = {},{}. error msg = {},{}", sourceResult, targetResult, sourceError, targetError);
        if (StringUtils.hasText(sourceError) || StringUtils.hasText(targetError)) {
            return sourceError + targetError;
        }
        Different different = makeDiff(sourceResult, sourceContent, targetResult, targetContent, session);
        save(different);
        return "";

    }

    private Different makeDiff(Map<String, String> source, String sourceContent,
                               Map<String, String> target, String targetContent, HttpSession session) {
        MapDifference<String, String> difference = Maps.difference(source, target);
        log.debug("difference by Maps = {}", difference);
        Different different = new Different()
                .setCompareTime(new Date())
                .setSource(sourceContent)
                .setTarget(targetContent);
        if (difference.areEqual()) {
            different.setOnlySource("").setOnlyTarget("").setDiffering("");
        }
        Map<String, String> onlyOnLeft = difference.entriesOnlyOnLeft();
        Map<String, String> onlyOnRight = difference.entriesOnlyOnRight();
        Map<String, MapDifference.ValueDifference<String>> entriesDiffering = difference.entriesDiffering();
        if (onlyOnLeft.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Set<String> keySet = onlyOnLeft.keySet();
            for (String key : keySet) {
                sb.append("-").append(key).append("=").append(onlyOnLeft.get(key)).append("<br>");
            }
            different.setOnlySource(sb.toString());
        }
        if (onlyOnRight.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Set<String> keySet = onlyOnRight.keySet();
            for (String key : keySet) {
                sb.append("+").append(key).append("=").append(onlyOnRight.get(key)).append("<br>");
            }
            different.setOnlyTarget(sb.toString());
        }
        if (entriesDiffering.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Set<String> keySet = entriesDiffering.keySet();
            for (String key : keySet) {
                sb.append("-").append(key).append("=").append(entriesDiffering.get(key).leftValue())
                        .append(";+").append(key).append("=").append(entriesDiffering.get(key).rightValue())
                        .append("<br>");
            }
            different.setDiffering(sb.toString());
        }
        User user = (User) session.getAttribute(Constrant.CURRENT_USER);
        if (user == null) {
            user = userMapper.findByUsername(Constrant.anonymous);
            session.setAttribute(Constrant.CURRENT_USER, user);
        }
        different.setUser(user);
        log.debug("different = {}", different);
        return different;
    }

    private class KeyValueLineProcessor implements LineProcessor<Map<String, String>> {
        private Map<String, String> map = Maps.newHashMap();
        private StringBuilder error = new StringBuilder();
        private String fileName;
        private StringBuilder content = new StringBuilder();

        private KeyValueLineProcessor(String fileName) {
            this.fileName = fileName;
        }

        private void reset(String fileName) {
            this.fileName = fileName;
            map = Maps.newHashMap();
            error = new StringBuilder();
            content = new StringBuilder();
        }

        private String getError() {
            return error.toString();
        }

        private String getContent() {
            return content.toString();
        }

        @Override
        public boolean processLine(String line) throws IOException {
            content.append(line).append("\r\n");
            String[] keyValue = line.split("=");
            log.trace("process line: {},[] = {}", line, Arrays.toString(keyValue));
            if (keyValue.length != 2) {
                error.append("文件内容必须是等号分割的键值对！【")
                        .append(fileName).append("】: ").append(line).append("<br>");
                return false;
            }
            map.put(keyValue[0], keyValue[1]);
            return true;
        }

        @Override
        public Map<String, String> getResult() {
            return map;
        }
    }
}
