package com.CM.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.protal.service.LoginService;
@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;
	@RequestMapping("/chat/user/loginOut")
	@ResponseBody
	public CMResult loginOut(String userId)
	{
		return loginService.loginOut(userId);
	}
}
