package com.youthlin.qunar.diff.dao;

import com.youthlin.qunar.diff.model.Different;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by youthlin.chen on 2016-11-16 016.
 * dao
 */
@Repository
public interface DifferentMapper {
    int save(Different different);

    Different findById(Integer did);

    List<Different> findByUserId(Integer uid);

    List<Different> findPageByUserId(@Param("uid") Integer uid, @Param("start") int rowStartExclude, @Param("size") int rowCount);

    int delete(@Param("did") Integer did, @Param("userId") Integer userId);
}
