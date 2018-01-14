package com.CM.protal.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.protal.service.UserService;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
@Service
public class UserServiceImpl implements UserService
{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_USER_ACTIVE_LIST}")
	private String REST_USER_ACTIVE_LIST;
	@Value("${REST_USER_CHECK}")
	private String REST_USER_CHECK;
	@Override
	public List<EasyUITreeNode> getActiverList()
	{
		//从rest中获得在线用户列表
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_USER_ACTIVE_LIST);
		//转换格式
		List<EasyUITreeNode> nodes = JsonUtils.jsonToList(json, EasyUITreeNode.class);
		return nodes;
	}
	@Override
	public CMResult check(String userId)
	{
		//从rest中确认用户是否还在线
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_USER_CHECK,param);
		return CMResult.format(json);
	}

}
