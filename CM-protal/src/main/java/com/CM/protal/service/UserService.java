package com.CM.protal.service;

import java.util.List;

import com.CM.common.pojo.CMResult;
import com.CM.common.pojo.EasyUITreeNode;


public interface UserService
{
	public List<EasyUITreeNode> getActiverList();
	public CMResult check(String userId);
}
