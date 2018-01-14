package com.CM.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.pojo.User;
import com.CM.sso.service.RegisterService;

@Controller
public class RegisterController
{
	@Autowired
	private RegisterService registerService;
	@RequestMapping("/sso/page/register")
	public String showRegisterPage(Model model,String redirect)
	{
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("redirect",redirect);
		return "register";
	}
	@RequestMapping("/sso/user/register")
	@ResponseBody
	public CMResult register(User user)
	{
		return registerService.register(user);
	}
}
