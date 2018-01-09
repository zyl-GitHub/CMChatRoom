package com.CM.rest.component;

public interface JedisClient
{
	//增
	public String set(String key,String value);
	//查
	public String get(String key);
	public Long hset(String key,String item,String value);
	public String hget(String key,String item);
	public Long incr(String key);
	public Long decr(String key);
	public Long expire(String key,int second);
	public Long ttl(String key);
	public Long hdel(String key,String item);
}
