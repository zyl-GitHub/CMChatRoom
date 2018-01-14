package com.CM.rest.service.Impl;


import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.CM.common.pojo.CMResult;
import com.CM.rest.component.JedisClient;
import com.CM.rest.service.InfoService;
@Service
public class InfoServiceImpl implements InfoService
{
	@Autowired
	private JedisClient jedisClient;
	@Value("${MESSAGE_SPLIT}")
	private String MESSAGE_SPLIT;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_USER_LOGIN_KEY}")
	private String REDIS_USER_LOGIN_KEY;
	@Value("${REDIS_ACTIVE_USER_LIST_KEY}")
	private String REDIS_ACTIVE_USER_LIST_KEY;
	@Value("${REDIS_INFO_PERSONAL_KEY}")
	private String REDIS_INFO_PERSONAL_KEY;
	@Value("${REDIS_INFO_ALL_KEY}")
	private String REDIS_INFO_ALL_KEY;
	@Value("${REDIS_INFO_PERSONAL_COUNT_KEY}")
	private String REDIS_INFO_PERSONAL_COUNT_KEY;
	@Value("${REDIS_INFO_ALL_COUNT_KEY}")
	private String REDIS_INFO_ALL_COUNT_KEY;
	@Override
	public CMResult sendPersonalMessage(String message)
	{
		//得到发送消息的人
		String userIdb = message.split(MESSAGE_SPLIT)[1];
		String username = message.split(MESSAGE_SPLIT)[2];
		String userIdc = message.split(MESSAGE_SPLIT)[3];
		String content = message.split(MESSAGE_SPLIT)[4];
		//放入到redis
		//取到userIdMessagecount
		String userIdMessagecount = jedisClient.get(userIdc+":Messagecount");
		if(StringUtils.isEmpty(userIdMessagecount))
		{
			userIdMessagecount = "1";
		}
		else
		{
			userIdMessagecount = String.valueOf(Long.parseLong(userIdMessagecount)+1L);
		}
		jedisClient.set(userIdc+":Messagecount", userIdMessagecount);
		//拼接key Hset userId(接收方id) userIdmessagecount message
		String key  = REDIS_SESSION_KEY+":"+REDIS_INFO_PERSONAL_KEY+":"+userIdc;
		String item = userIdMessagecount;
		//1+userIdb(发送方Id)+发送方姓名+userIdc(接收方Id)+message 发送到后端
		//'1'+"*@*&"+userIdb+"*@*"+${user.name}+"*@*&"+userIdc+"*@*&"+value;
		String value = "";
		try
		{
			value = userIdb+MESSAGE_SPLIT+new String(username.getBytes("ISO-8859-1"))+MESSAGE_SPLIT+new String(content.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		jedisClient.hset(key, item, value);
		return CMResult.ok();
	}

	@Override
	public CMResult sendAllMessage(String message)
	{
		//0*@*&10000*@*&赵银龙*@*&test
		//'0'+useId(发送方id)+发送方的姓名+message 发送到后端
		//message = '0'+"*@*&"+userIdb+"*@*"+${user.name}+"*@*&"+value;
		String content = message.split(MESSAGE_SPLIT)[3];
		String value = "";
		try
		{
			value = "群聊"+MESSAGE_SPLIT+new String(content.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//放入到redis
		//取到userIdMessagecount
		String messagecount = jedisClient.get("messagecount");
		if(StringUtils.isEmpty(messagecount))
		{
			messagecount = "1";
		}
		else
		{
			messagecount = String.valueOf(Long.parseLong(messagecount)+1L);
		}
		jedisClient.set("messagecount", messagecount);
		String key = REDIS_SESSION_KEY+":"+REDIS_INFO_ALL_KEY+":"+messagecount;
		jedisClient.set(key, value);
		return CMResult.ok();
	}

	@Override
	public CMResult showPersonalMessage(String userId)
	{
		CMResult result = new CMResult();
		result.setStatus(500);
		//得到当前用户已经得到的消息数目
		//拼接key
		String key = REDIS_SESSION_KEY+":"+REDIS_INFO_PERSONAL_KEY+":"+REDIS_INFO_PERSONAL_COUNT_KEY+":"+userId;
		String currentMessageCount = jedisClient.get(key);
		if(StringUtils.isEmpty(currentMessageCount))
		{
			currentMessageCount = "0";
		}
		//得到用户总共收到的消息数目
		String messagecount = jedisClient.get(userId+":Messagecount");
		if(StringUtils.isEmpty(messagecount))
		{
			messagecount = "0";
		}
		if(!messagecount.equals(currentMessageCount))
		{
			currentMessageCount = String.valueOf(Long.parseLong(currentMessageCount)+1);
			//增加收到的消息数目
			jedisClient.set(key, currentMessageCount);
			//拼接key Hset userId(接收方id) userIdmessagecount message
			String k  = REDIS_SESSION_KEY+":"+REDIS_INFO_PERSONAL_KEY+":"+userId;
			String item = currentMessageCount;
			//得到消息
			String content = jedisClient.hget(k, item);
			//返回
			result.setStatus(200);
			result.setData(content);
		}
		return result;
	}

	@Override
	public CMResult showAllMessage(String userId)
	{
		CMResult result = new CMResult();
		result.setStatus(500);
		//得到用户已经收到的群聊消息数
		//拼接key
		String key = REDIS_SESSION_KEY+":"+REDIS_INFO_PERSONAL_KEY+":"+REDIS_INFO_ALL_COUNT_KEY+":"+userId;
		//得到消息数目
		String currentMessageCount = jedisClient.get(key);
		if(StringUtils.isEmpty(currentMessageCount))
		{
			currentMessageCount = "0";
		}
		//得到用户总共收到群聊的消息数目
		String messagecount = jedisClient.get("messagecount");
		if(StringUtils.isEmpty(messagecount))
		{
			messagecount = "0";
		}
		if(!messagecount.equals(currentMessageCount))
		{
			currentMessageCount = String.valueOf(Long.parseLong(currentMessageCount)+1);
			//增加收到的消息数目
			jedisClient.set(key,currentMessageCount);
			//得到消息
			String k = REDIS_SESSION_KEY+":"+REDIS_INFO_ALL_KEY+":"+currentMessageCount;
			String content = jedisClient.get(k);
			//设置返回值
			result.setStatus(200);
			result.setData(content);
		}
		
		return result;
	}

}
