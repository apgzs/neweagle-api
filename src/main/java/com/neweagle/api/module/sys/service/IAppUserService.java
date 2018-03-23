package com.neweagle.api.module.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.neweagle.api.module.sys.entity.AppUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 *
 * AppUser 表数据服务层接口
 *
 */
public interface IAppUserService extends IService<AppUser> {

	boolean deleteAll();

	public List<AppUser> selectListBySQL();

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