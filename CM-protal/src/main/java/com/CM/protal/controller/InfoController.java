package com.CM.protal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.protal.service.InfoService;

@Controller
public class InfoController
{
	@Autowired
	private InfoService infoService;
	@RequestMapping("/chat/content/sendMessage")
	@ResponseBody
	public CMResult sendMessage(String message)
	{
		return infoService.sendMessage(message);
	}
	@RequestMapping("/chat/content/showPersonMessage")
	@ResponseBody
	public CMResult showPersonMessage(String userId,HttpServletRequest request,HttpServletResponse response)
	{
		response.setCharacterEncoding("utf-8");
		response.setHeader("contentType", "text/html;charset=utf-8");
		return infoService.showPersonalMessage(userId);
	}
	@RequestMapping("/chat/content/showAllMessage")
	@ResponseBody
	public CMResult showAllMessage(String userId,HttpServletRequest request,HttpServletResponse response)
	{
		response.setCharacterEncoding("utf-8");
		response.setHeader("contentType", "text/html;charset=utf-8");
		CMResult result = infoService.showAllMessage(userId);
		return result;
	}
}
