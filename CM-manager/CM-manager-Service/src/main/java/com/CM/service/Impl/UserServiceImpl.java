package com.CM.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CM.mapper.UserMapper;
import com.CM.pojo.User;
import com.CM.pojo.UserExample;
import com.CM.service.UserService;
import com.taotao.common.utils.JsonUtils;
/**
 * 用户信息管理service
 * @author zhao
 *
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;
	@Override
	public String getUserById(String userId)
	{
		String json = "";
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> list = userMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			User user = list.get(0);
			json = JsonUtils.objectToJson(user);
		}
		return json;
	}

}
