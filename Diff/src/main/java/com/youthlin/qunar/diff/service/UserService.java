package com.youthlin.qunar.diff.service;

import com.youthlin.qunar.diff.common.Constrant;
import com.youthlin.qunar.diff.dao.UserMapper;
import com.youthlin.qunar.diff.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * User 的相关操作
 */
@Service
public class UserService {
    private static final String account = "account.properties";
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserMapper userMapper;
    private boolean accountInit = false;

    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    public User login(String username, String password) {
        return userMapper.login(username, password);

    }

    public User findByUsername(String username) {
        if (!accountInit) {
            insert();
        }
        return userMapper.findByUsername(username);
    }

    /*把配置文件中账号存入数据库*/
    private void insert() {
        Properties properties = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(account);
        try {
            properties.load(in);
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String name = key.toString();
                String pass = properties.getProperty(name);
                if (userMapper.findByUsername(name) == null) {
                    save(new User().setUsername(name).setPassword(pass));
                }
            }
        } catch (IOException e) {
            log.warn("Can not load {}.", account, e);
        }
        accountInit = true;
    }

    public User getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(Constrant.CURRENT_USER);
        if (user == null) {
            user = findByUsername(Constrant.anonymous);
        }
        return user;
    }
}
