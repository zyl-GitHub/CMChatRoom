package com.CM.protal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.CM.common.pojo.CMResult;
import com.CM.protal.service.LoginService;
import com.taotao.common.utils.HttpClientUtil;
@Service
public class LoginServiceImpl implements LoginService
{
	@Value("${SSO_LOGINOUT_URL}")
	private String SSO_LOGINOUT_URL;
	@Override
	public CMResult loginOut(String userId)
	{
		//向sso发送下线
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		String json = HttpClientUtil.doGet(SSO_LOGINOUT_URL, param);
		CMResult result = CMResult.format(json);
		return result;
	}

}
