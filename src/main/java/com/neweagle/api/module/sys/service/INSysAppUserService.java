package com.neweagle.api.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.neweagle.api.module.sys.entity.NSysAppUser;

import java.util.List;

/**
 *
 * NSysAppUser 表数据服务层接口
 *
 */
public interface INSysAppUserService extends IService<NSysAppUser> {

	boolean deleteAll();

	public List<NSysAppUser> selectListBySQL();

	/**
	 * 注册
	 * @param mobile
	 * @param verifyCode
	 * @param password
	 * @return
	 * @throws Exception
	 */
	boolean register(String mobile,String verifyCode,String password) throws Exception;
}