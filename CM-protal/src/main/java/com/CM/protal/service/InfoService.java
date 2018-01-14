package com.CM.protal.service;

import com.CM.common.pojo.CMResult;

public interface InfoService
{
	public CMResult sendMessage(String message);
	public CMResult showPersonalMessage(String userId);
	public CMResult showAllMessage(String userId);
}
