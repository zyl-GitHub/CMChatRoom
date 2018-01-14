package com.CM.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CM.common.pojo.CMResult;

public interface LoginService
{
	public CMResult login(String userId,String password,HttpServletRequest request,HttpServletResponse response);
	public CMResult loginOut(String userId,HttpServletRequest request,HttpServletResponse response);
}
