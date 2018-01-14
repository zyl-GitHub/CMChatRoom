package com.CM.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.rest.service.OnlineService;
/**
 * 在线有关的controller
 * @author zhao
 *
 */
@Controller
public class OnlineController
{
	@Autowired
	private OnlineService onlineService;
	/**
	 * 得到在线用户列表
	 * @return
	 */
	@RequestMapping("/rest/online/getUserList")
	@ResponseBody
	public List<EasyUITreeNode> getUserList()
	{
		return onlineService.getUserList();
	}
	
}
