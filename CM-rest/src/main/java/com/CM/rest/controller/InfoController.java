package com.CM.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.rest.service.InfoService;

@Controller
public class InfoController
{
	@Autowired
	private InfoService infoService;
	@RequestMapping("/rest/info/sendALLMessage")
	@ResponseBody
	public CMResult sendAllMessage(String message)
	{
		return infoService.sendAllMessage(message);
	}
	@RequestMapping("/rest/info/sendPersonalMessage")
	@ResponseBody
	public CMResult sendPersonalMessage(String message)
	{
		return infoService.sendPersonalMessage(message);
	}
	@RequestMapping("/rest/info/showAllMessage")
	@ResponseBody
	public CMResult showAllMessage(String userId)
	{
		return infoService.showAllMessage(userId);
	}
	@RequestMapping("/rest/info/showPersonalMessage")
	@ResponseBody
	public CMResult showPersonalMessage(String userId,HttpServletRequest request)
	{
		return infoService.showPersonalMessage(userId);
	}
}	
