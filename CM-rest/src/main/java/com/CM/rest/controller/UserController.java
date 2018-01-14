package com.CM.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.pojo.User;
import com.CM.rest.service.UserService;
/**
 * 和用户有关的controller
 * @author zhao
 *
 */
@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/rest/user/update")
	@ResponseBody
	public CMResult updateUser(User user)
	{
		return userService.update(user);
	}
	
	@RequestMapping("/rest/user/getUserByUserId")
	@ResponseBody
	public User getUserByUserId(String userId)
	{
		return userService.getUserByUserId(userId);
	}
	
	@RequestMapping("/rest/user/check")
	@ResponseBody
	public CMResult check(String userId)
	{
		return userService.checkOnline(userId);
	}
	@RequestMapping("/rest/user/token")
	@ResponseBody
	public User getUserByToken(String Token)
	{
		return userService.getUserByToken(Token);
	}
}
