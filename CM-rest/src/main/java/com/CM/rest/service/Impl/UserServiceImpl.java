package com.CM.rest.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.CM.common.pojo.CMResult;
import com.CM.mapper.UserMapper;
import com.CM.pojo.User;
import com.CM.pojo.UserExample;
import com.CM.rest.component.JedisClient;
import com.CM.rest.service.UserService;
import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.utils.JsonUtils;
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private UserMapper userMapper;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_USER_LOGIN_KEY}")
	private String REDIS_USER_LOGIN_KEY;
	@Value("${REDIS_ACTIVE_USER_LIST_KEY}")
	private String REDIS_ACTIVE_USER_LIST_KEY;
	@Override
	public CMResult update(User user)
	{
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(user.getUserId());
		userMapper.updateByExampleSelective(user, example);
		return CMResult.ok();
	}

	@Override
	public User getUserByUserId(String userId)
	{
		String json = "";
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public CMResult checkOnline(String userId)
	{
		CMResult result = new CMResult();
		result.setStatus(500);
		//从数据库里查询是否存在该用户
		//拼接key
		String key = REDIS_SESSION_KEY+":"+REDIS_USER_LOGIN_KEY+":"+userId;
		//查询
		String userjson = jedisClient.get(key);
		if(!StringUtils.isEmpty(userjson))
		{
			
			result.setData(JSONUtils.toJSONString(userjson));
			result.setStatus(200);
		}
		return result;
	}

	@Override
	public User getUserByToken(String Token)
	{
		String json = jedisClient.get(Token);
		if(!StringUtils.isEmpty(json))
		{
			User user = this.getUserByUserId(json);
			return user;
		}
		
		return null;
	}

}
