package com.CM.protal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CM.common.pojo.CMResult;
import com.CM.protal.service.InfoService;
import com.taotao.common.utils.HttpClientUtil;
@Service
public class InfoServiceImpl implements InfoService
{
	@Value("${MESSAGE_SPLIT}")
	private String MESSAGE_SPLIT;
	@Value("${REST_INFO_SEND_ALL}")
	private String REST_INFO_SEND_ALL;
	@Value("${REST_INFO_SEND_PERSONAL}")
	private String REST_INFO_SEND_PERSONAL;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INFO_SHOW_ALL}")
	private String REST_INFO_SHOW_ALL;
	@Value("${REST_INFO_SHOW_PERSONAL}")
	private String REST_INFO_SHOW_PERSONAL;
	private Object o1 = new Object();
	private Object o2 = new Object();
	//群聊 '0'+"*@*&"+userIdb+"*@*"+${user.name}+"*@*&"+value;
	//私聊 '1'+"*@*&"+userIdb+"*@*"+${user.name}+"*@*&"+userIdc+"*@*&"+value;
	@Override
	public CMResult sendMessage(String message)
	{
		CMResult result = new CMResult();
		Map<String, String> param = new HashMap<String, String>();
		param.put("message", message);
		// 区分私聊和群聊
		String mark = message.split(MESSAGE_SPLIT)[0];
		if(mark.equals("0"))
		{
			//群聊
			String json = "";
			synchronized (o1)
			{
				json = HttpClientUtil.doGet(REST_BASE_URL+REST_INFO_SEND_ALL,param);	
			}
			result = CMResult.format(json);
		}
		else
		{
			//私聊
			String json = "";
			synchronized (o2)
			{
				json = HttpClientUtil.doGet(REST_BASE_URL+REST_INFO_SEND_PERSONAL,param);	
			}
			result = CMResult.format(json);
		}
		return result;
	}

	@Override
	public CMResult showPersonalMessage(String userId)
	{
		//从rest中获取消息
		CMResult result = new CMResult();
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		synchronized (o1)
		{
			String json = HttpClientUtil.doGet(REST_BASE_URL+REST_INFO_SHOW_PERSONAL,param);
			result = CMResult.format(json);
		}
		return result;
	}

	@Override
	public CMResult showAllMessage(String userId)
	{
		//从rest中获取消息
		CMResult result = new CMResult();
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		String json= "";
		synchronized (o1)
		{
			json = HttpClientUtil.doGet(REST_BASE_URL+REST_INFO_SHOW_ALL,param);
			result = CMResult.format(json);
		}
		return result;
	}

}
