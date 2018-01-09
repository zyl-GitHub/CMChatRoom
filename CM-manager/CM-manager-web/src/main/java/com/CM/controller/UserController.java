package com.CM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.service.UserService;
/**
 * 用户信息管理controller
 * @author zhao
 *
 */
@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{userId}")
	public String getUserId(@PathVariable String userId)
	{
		return userService.getUserById(userId);
	}
}
