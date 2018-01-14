package com.CM.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.sso.service.LoginService;
/**
 * 登陆Controller
 * @author zhao
 *
 */
@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;
	@RequestMapping("/sso/page/login")
	public String showLoginPage(String redirect,Model model)
	{
		model.addAttribute("redirect",redirect);
		return "login";
	}
	@RequestMapping("/sso/user/login")
	@ResponseBody
	public CMResult login(String userId,String password,Model model,HttpServletRequest request,HttpServletResponse response)
	{
		CMResult result = loginService.login(userId, password,request,response);
		return result;
	}
	@RequestMapping("/sso/user/loginOut")
	@ResponseBody
	public CMResult loginOut(String userId,HttpServletRequest request,HttpServletResponse response)
	{
		return loginService.loginOut(userId,request,response);
	}
}
