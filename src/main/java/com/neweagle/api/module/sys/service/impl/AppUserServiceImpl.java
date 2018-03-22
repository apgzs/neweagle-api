package com.neweagle.api.module.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.neweagle.api.module.sys.entity.AppUser;
import com.neweagle.api.module.sys.mapper.AppUserMapper;
import com.neweagle.api.module.sys.service.IAppUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * AppUser 表数据服务层接口实现类
 *
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<AppUser> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

}