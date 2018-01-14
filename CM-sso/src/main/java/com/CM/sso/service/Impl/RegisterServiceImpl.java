package com.CM.sso.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.CM.common.pojo.CMResult;
import com.CM.mapper.UserMapper;
import com.CM.pojo.User;
import com.CM.pojo.UserExample;
import com.CM.sso.service.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired
	private UserMapper userMapper;
	@Override
	public CMResult register(User user)
	{
		CMResult result = new CMResult();
		//数据校验
		//账号唯一
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getUserId());
		List<User> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			result.setMsg("账号已存在");
			result.setStatus(500);
			return result;
		}
		//密码加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		//插入
		userMapper.insert(user);
		result.setMsg("注册成功");
		result.setStatus(200);
		return result;
	}

}
