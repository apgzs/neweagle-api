package com.neweagle.api.module.sys.entity;

import com.neweagle.api.comm.enums.EnableEnum;
import com.neweagle.api.comm.plugin.mybatisplus.SuperEntity;
import lombok.Data;


/**
 * 用户表
 */
@Data
public class AppUser extends SuperEntity<AppUser> {
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 冻结
     */
    private EnableEnum enabled;


}
