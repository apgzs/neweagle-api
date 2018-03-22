package com.neweagle.api.module.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.neweagle.api.module.sys.entity.User;
import com.neweagle.api.module.sys.mapper.UserMapper;
import com.neweagle.api.module.sys.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

}