package com.CM.protal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.protal.service.UserService;
@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	@RequestMapping("/chat/online/activerlist")
	@ResponseBody
	public List<EasyUITreeNode> showActiverList()
	{
		List<EasyUITreeNode> list = new ArrayList<>();
		list = userService.getActiverList();
		return list;
	}
	@RequestMapping("/chat/user/check")
	@ResponseBody
	public CMResult check(String userId)
	{
		return userService.check(userId);
	}
}
