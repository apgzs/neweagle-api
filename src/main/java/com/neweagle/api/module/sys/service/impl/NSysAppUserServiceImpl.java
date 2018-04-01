package com.neweagle.api.module.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.neweagle.api.comm.exception.UserExistException;
import com.neweagle.api.module.sys.entity.NSysAppUser;
import com.neweagle.api.module.sys.mapper.NSysAppUserMapper;
import com.neweagle.api.module.sys.service.INSysAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * NSysAppUser 表数据服务层接口实现类
 *
 */
@Service
public class NSysAppUserServiceImpl extends ServiceImpl<NSysAppUserMapper, NSysAppUser> implements INSysAppUserService {
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
	public List<NSysAppUser> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	@Override
	public boolean register(String mobile, String verifyCode, String password) throws Exception {
		NSysAppUser user = new NSysAppUser();
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