package com.CM.rest.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.pojo.User;
import com.CM.rest.component.JedisClient;
import com.CM.rest.service.OnlineService;
import com.taotao.common.utils.JsonUtils;
@Service
public class OnlineServiceImpl implements OnlineService
{
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_USER_LOGIN_KEY}")
	private String REDIS_USER_LOGIN_KEY;
	@Value("${REDIS_ACTIVE_USER_LIST_KEY}")
	private String REDIS_ACTIVE_USER_LIST_KEY;
	@Override
	public List<EasyUITreeNode> getUserList()
	{
		List<EasyUITreeNode> easyUITreeNodes = new ArrayList<EasyUITreeNode>();
		// 从redis中获得在线用户列表
		//拼接key
		String userlistKey = REDIS_SESSION_KEY+":"+REDIS_ACTIVE_USER_LIST_KEY;
		//得到list
		String userListJson = jedisClient.get(userlistKey);
		List<User> list = JsonUtils.jsonToList(userListJson, User.class);
		if(list!=null&&list.size()>0)
		{
			for(User user:list)
			{
				EasyUITreeNode node = new EasyUITreeNode();
				node.setId(Long.parseLong(user.getUserId()));
				node.setText(user.getName());
				easyUITreeNodes.add(node);
			}
		}
		return easyUITreeNodes;
	}

}
