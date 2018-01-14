package com.CM.sso.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.CM.common.pojo.CMResult;
import com.CM.common.utils.CookieUtils;
import com.CM.mapper.UserMapper;
import com.CM.pojo.User;
import com.CM.pojo.UserExample;
import com.CM.sso.component.JedisClient;
import com.CM.sso.service.LoginService;
import com.taotao.common.utils.JsonUtils;
@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_USER_LOGIN_KEY}")
	private String REDIS_USER_LOGIN_KEY;
	@Value("${REDIS_ACTIVE_USER_LIST_KEY}")
	private String REDIS_ACTIVE_USER_LIST_KEY;
	@Override
	public CMResult login(String userId,String password,HttpServletRequest request,HttpServletResponse response)
	{
		User user = null;
		CMResult result = new CMResult();
		//密码加密
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		//添加查询条件
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andPasswordEqualTo(password);
		//执行查询
		List<User> list = userMapper.selectByExample(example);
		//判断是否存在该用户
		if(list!=null&&list.size()>0)
		{
			//取出该用户的完整信息
			user = list.get(0);
			//将该用户添加到redis中，记录该用户已经登陆
			//拼接key
			String key = REDIS_SESSION_KEY+":"+REDIS_USER_LOGIN_KEY+":"+user.getUserId();
			CookieUtils.setCookie(request, response, "CM_TOKEN", key);
			//执行添加
			jedisClient.set(key, user.getUserId());
			//取出在线的用户列表
				String userlistKey = REDIS_SESSION_KEY+":"+REDIS_ACTIVE_USER_LIST_KEY;
				String userListJson = jedisClient.get(userlistKey);
				List<User> userList = new ArrayList<User>();
				if(!StringUtils.isEmpty(userListJson))
				{
					userList = JsonUtils.jsonToList(userListJson, User.class);
				}
				//将该用户添加到在线用户列表中
				userList.add(user);
				userListJson = JsonUtils.objectToJson(userList);
				//放回redis中
				if(!StringUtils.isEmpty(userListJson))
				{
					jedisClient.del(userlistKey);
				}
				jedisClient.set(userlistKey, userListJson);
			//设定登陆成功的结果集
			String json = JsonUtils.objectToJson(user);
			result.setStatus(200);
			result.setData(json);
		}
		else
		{
			//用户不存在,设定登陆不成功的结果集
			result.setStatus(500);
		}
		return result;
	}
	@Override
	public CMResult loginOut(String userId,HttpServletRequest request,HttpServletResponse response)
	{
		CMResult result = new CMResult();
		//从redis中删除该用户
		String key = REDIS_SESSION_KEY+":"+REDIS_USER_LOGIN_KEY+":"+userId;
		jedisClient.del(key);
		//从在线列表中删除该用户
		//取出在线的用户列表
			String userlistKey = REDIS_SESSION_KEY+":"+REDIS_ACTIVE_USER_LIST_KEY;
			String userListJson = jedisClient.get(userlistKey);
			List<User> userList = JsonUtils.jsonToList(userListJson, User.class);
			//移除该用户
			for(User user:userList)
			{
				if(user.getUserId().equals(userId))
				{
					userList.remove(user);
					break;
				}
			}
			//将列表放回redis
			userListJson = JsonUtils.objectToJson(userList);
			jedisClient.del(userlistKey);
			jedisClient.set(userlistKey, userListJson);
		CookieUtils.deleteCookie(request, response, "CM_TOKEN");
		result.setStatus(200);
		result.setMsg("退出成功");
		return result;
	}
}
