package com.neweagle.api.module.sys.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;

import com.neweagle.api.comm.plugin.mybatisplus.SuperMapper;
import com.neweagle.api.module.sys.entity.AppUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * AppUser 表数据库控制层接口
 */
public interface AppUserMapper extends SuperMapper<AppUser> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     */
    @SqlParser(filter = true)
    @Select("select test_id as id, name, age, test_type from user")
    List<AppUser> selectListBySQL();

}