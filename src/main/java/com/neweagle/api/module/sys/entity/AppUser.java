package com.neweagle.api.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.neweagle.api.comm.enums.EnableEnum;
import com.neweagle.api.comm.plugin.mybatisplus.SuperEntity;
import lombok.Data;


/**
 * 用户表
 */
@Data
@TableName("n_sys_app_user")
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
    @TableField(value = "enabled",fill = FieldFill.INSERT)
    private EnableEnum enabled;


}
