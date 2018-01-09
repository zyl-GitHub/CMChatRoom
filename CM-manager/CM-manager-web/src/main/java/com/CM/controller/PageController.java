package com.CM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 页面controller
 * @author zhao
 *
 */
@Controller
public class PageController
{
	/**
	 * 显示首页
	 * @return
	 */
	@RequestMapping("/")
	public String ShowPage()
	{
		return "index";
	}
	
	/**
	 * 显示指定页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String ShowPage(@PathVariable String page)
	{
		return page;
	}
}
