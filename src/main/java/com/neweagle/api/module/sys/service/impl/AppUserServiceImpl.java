package com.neweagle.api.module.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.neweagle.api.comm.exception.UserExistException;
import com.neweagle.api.module.sys.entity.AppUser;
import com.neweagle.api.module.sys.mapper.AppUserMapper;
import com.neweagle.api.module.sys.service.IAppUserService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * AppUser 表数据服务层接口实现类
 *
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {
	/**
	 * 密码加密
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<AppUser> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	@Override
	public boolean register(String mobile, String verifyCode, String password) throws Exception {
		AppUser user = new AppUser();
		user.setUsername(mobile);
		if (baseMapper.selectOne(user)!=null){
			throw new UserExistException();
		}
		user.setMobile(mobile);
		user.setNickname(mobile);
		user.setPassword(passwordEncoder.encode(password));
		return retBool(baseMapper.insert(user));
	}

}