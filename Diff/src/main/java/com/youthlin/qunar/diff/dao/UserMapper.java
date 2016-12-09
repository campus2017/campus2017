package com.youthlin.qunar.diff.dao;

import com.youthlin.qunar.diff.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * User dao
 */
@Repository
public interface UserMapper {
    int save(User user);

    User login(@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);
}
