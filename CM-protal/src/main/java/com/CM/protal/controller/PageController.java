package com.CM.protal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;
import com.CM.pojo.File;
import com.CM.pojo.User;

@Controller
public class PageController
{
	@RequestMapping("/chat/main")
	public String showMain(Model model)
	{
		User user = new User();
		user.setUserId("10000");
		user.setName("赵银龙");
		user.setTypeId(0);
		user.setAge(19);;
		user.setId(1);
		List<File> fileList = new ArrayList<>();
		File file = new File();
		file.setCount(100);
		file.setId(1);
		file.setLink("http://www.baidu.com");
		file.setName("UNO");
		fileList.add(file);
		model.addAttribute("user",user);
		model.addAttribute("fileList",fileList);
		return "main";
	}
	@RequestMapping("/chat/online/showOnline")
	public String showOnline()
	{
		return "online";
	}
	@RequestMapping("/chat/online/activerlist")
	@ResponseBody
	public List<EasyUITreeNode> showActiverList()
	{
		List<EasyUITreeNode> list = new ArrayList<>();
		EasyUITreeNode node = new EasyUITreeNode();
		User user = new User();
		user.setUserId("10000");
		user.setName("赵银龙");
		user.setTypeId(0);
		user.setAge(19);;
		user.setId(1);
		node.setText(user.getName());
		node.setId(Long.parseLong(user.getUserId()));
		list.add(node);
		EasyUITreeNode node1 = new EasyUITreeNode();
		User user1 = new User();
		user1.setUserId("10001");
		user1.setName("季祥");
		user1.setTypeId(2);
		user1.setAge(19);;
		user1.setId(2);
		node1.setText(user1.getName());
		node1.setId(Long.parseLong(user1.getUserId()));
		list.add(node1);
		return list;
	}
	@RequestMapping("/chat/content/showPersonMessage")
	@ResponseBody
	public CMResult showPersonMessage()
	{
		CMResult result = new CMResult();
		result.setData("10001*@*&季祥*@*&你好！！！");
		result.setStatus(200);
		return result;
	}
	@RequestMapping("/chat/content/showAllMessage")
	@ResponseBody
	public CMResult showAllMessage()
	{
		CMResult result = new CMResult();
		result.setData("群聊*@*&你好！！！");
		result.setStatus(200);
		return result;
	}
}
