package com.neweagle.api.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.neweagle.api.module.sys.entity.User;

import java.util.List;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	boolean deleteAll();

	public List<User> selectListBySQL();
}