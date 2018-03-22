package com.neweagle.api.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.neweagle.api.module.sys.entity.AppUser;

import java.util.List;

/**
 *
 * AppUser 表数据服务层接口
 *
 */
public interface IAppUserService extends IService<AppUser> {

	boolean deleteAll();

	public List<AppUser> selectListBySQL();
}