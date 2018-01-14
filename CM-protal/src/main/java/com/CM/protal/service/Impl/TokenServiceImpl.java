package com.CM.protal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CM.protal.service.TokenService;
import com.taotao.common.utils.HttpClientUtil;
@Service
public class TokenServiceImpl implements TokenService
{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_USER_TOKEN}")
	private String REST_USER_TOKEN;
///rest/user/token
	@Override
	public String getUserByToken(String Token)
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("Token", Token);
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_USER_TOKEN, param);
		return json;
	}

}
