package com.CM.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.pojo.User;
import com.CM.rest.service.UserService;
import com.taotao.common.utils.JsonUtils;

public class TokenController
{
	@Autowired
	private UserService userService;
	@RequestMapping("/rest/user/token")
	@ResponseBody
	public User getUserByToken(String Token)
	{
		return userService.getUserByToken(Token);
	}
}
