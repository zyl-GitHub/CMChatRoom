package com.CM.protal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.pojo.File;
import com.CM.pojo.User;
import com.taotao.common.utils.JsonUtils;

@Controller
public class PageController
{
	@RequestMapping("/chat/main")
	public String showMain()
	{
		return "main";
	}
	@RequestMapping("/chat/online/showOnline")
	public String showOnline()
	{
		return "online";
	}
	
	
}
