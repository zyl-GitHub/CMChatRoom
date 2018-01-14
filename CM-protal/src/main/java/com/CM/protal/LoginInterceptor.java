package com.CM.protal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.CM.common.pojo.CMResult;
import com.CM.common.utils.CookieUtils;
import com.CM.pojo.User;
import com.CM.protal.service.TokenService;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;

public class LoginInterceptor implements HandlerInterceptor
{
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	@Autowired
	private TokenService tokenService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception
	{
		String CM_TOKEN = CookieUtils.getCookieValue(request, "CM_TOKEN");
		if(CM_TOKEN == null)
		{
			response.sendRedirect(SSO_LOGIN_URL+"?redirect="+getUrl(request));
			return false;
		}
		else
		{
			String json = tokenService.getUserByToken(CM_TOKEN);
			User user = JsonUtils.jsonToPojo(json, User.class);
			if(user == null)
			{
				response.sendRedirect(SSO_LOGIN_URL+"?redirect="+getUrl(request));
				return false;
			}
			else
			{
				request.setAttribute("user", user);
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		// TODO 自动生成的方法存根
		
	}
	private String getUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" 
				+ request.getServerName() + ":" 
				+ request.getServerPort() 
				+ request.getContextPath() 
				+ request.getRequestURI(); 
		String url2 = request.getRequestURL().toString();
		System.out.println(url2);
		return url;
	}
}
