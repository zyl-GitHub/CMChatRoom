package com.CM.rest.service;

import com.CM.common.pojo.CMResult;

public interface InfoService
{
	public CMResult sendPersonalMessage(String message);
	public CMResult sendAllMessage(String message);
	public CMResult showPersonalMessage(String userId);
	public CMResult showAllMessage(String userId);
}
