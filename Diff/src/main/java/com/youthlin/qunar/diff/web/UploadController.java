package com.youthlin.qunar.diff.web;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.youthlin.qunar.diff.common.Constrant;
import com.youthlin.qunar.diff.model.Different;
import com.youthlin.qunar.diff.model.User;
import com.youthlin.qunar.diff.service.DifferentService;
import com.youthlin.qunar.diff.service.UserService;
import com.youthlin.qunar.diff.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * 上传文件/比较结果
 */
@Controller
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    private static final String ERROR = "error";
    private static final String UPLOAD = "upload";
    private static final String redirect = "redirect:";
    private static final String LIST = "list";
    @Resource
    private UserService userService;
    @Resource
    private DifferentService differentService;

    @RequestMapping(value = {LIST}, method = {RequestMethod.GET})
    public String upload(@RequestParam(value = "error", required = false, defaultValue = "") String error,
                         HttpSession session, Model model) {
        model.addAttribute(ERROR, error);
        addListToModel(session, model);
        return UPLOAD;
    }

    private void addListToModel(HttpSession session, Model model) {
        User currentUser = userService.getCurrentUser(session);
        List<Different> differentList = differentService.findByUserId(currentUser.getUserId());
        differentList = Lists.newArrayList(Collections2.filter(differentList, new Predicate<Different>() {
                    int count = 0;

                    @Override
                    public boolean apply(Different input) {
                        if (++count > 5) return false;
                        input.setSource(input.getSource().replace("\r\n", "<br>"));
                        input.setTarget(input.getTarget().replace("\r\n", "<br>"));
                        return true;
                    }
                }
        ));
        log.debug("list = {}", differentList);
        model.addAttribute(LIST, differentList);
        model.addAttribute(Constrant.CURRENT_USER, currentUser);
    }

    @RequestMapping(value = {UPLOAD}, method = {RequestMethod.POST})
    public String upload(@RequestParam("source-file") MultipartFile sourceFile,
                         @RequestParam("target-file") MultipartFile targetFile,
                         HttpSession session, Model model) throws IOException {
        if (sourceFile.isEmpty() || targetFile.isEmpty()) {
            log.debug("没有上传文件");
            model.addAttribute(ERROR, "源文件与目标文件都需要上传！");
            return redirect + LIST;
        }
        long sourceFileSize = sourceFile.getSize();
        long targetFileSize = targetFile.getSize();
        if (sourceFileSize > 1000 || targetFileSize > 1000) {
            log.debug("文件太大");
            model.addAttribute(ERROR, "文件大小应在 1KB 内");
            return redirect + LIST;
        }

        log.debug("文件符合规则");

        String errorMsg = differentService.saveDifferent(sourceFile, targetFile, session);

        model.addAttribute(ERROR, errorMsg);
        return redirect + LIST;
    }

    @RequestMapping(value = {"delete"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result delete(@RequestParam(value = "userId", defaultValue = "0") Integer userId,
                         @RequestParam(value = "did", defaultValue = "0") Integer did) {
        Result result = new Result().setCode(0).setData("删除成功");
        if (userId == 0 || did == 0) {
            result.setCode(1).setData("参数错误(userId/did)");
            return result;
        }
        int delete = differentService.delete(did, userId);
        log.debug("删除记录{}条，id = {}, userId = {},result = {}", delete, did, userId, result);
        return result;
    }
}
