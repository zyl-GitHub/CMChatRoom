package com.CM.rest.service;

import com.CM.common.pojo.CMResult;
import com.CM.pojo.User;

public interface UserService
{
	public CMResult update(User user);
	public User getUserByUserId(String userId);
	public CMResult checkOnline(String userId);
	public User getUserByToken(String Token);
}
