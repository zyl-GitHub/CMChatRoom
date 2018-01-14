package com.CM.sso.component.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.CM.sso.component.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient
{
	@Autowired
	private JedisPool jedisPool;
	@Override
	public  String set(String key, String value)
	{
		Jedis jedis = jedisPool.getResource();
		value = jedis.set(key, value);
		jedis.close();
		return value;
	}

	@Override
	public  String get(String key)
	{
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public  Long hset(String key, String item, String value)
	{
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}

	@Override
	public  String hget(String key, String item)
	{
		Jedis jedis = jedisPool.getResource();
		String value = jedis.hget(key, item);
		jedis.close();
		return value;
	}

	@Override
	public  Long incr(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.incr(key);
		jedis.close();
		return value;
	}

	@Override
	public  Long decr(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.decr(key);
		jedis.close();
		return value;
	}

	@Override
	public  Long expire(String key, int second)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.expire(key, second);
		jedis.close();
		return value;
	}

	@Override
	public  Long ttl(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.ttl(key);
		jedis.close();
		return value;
	}

	@Override
	public  Long hdel(String key, String item)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.hdel(key, item);
		jedis.close();
		return value;
	}

	@Override
	public  Long del(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.del(key);
		jedis.close();
		return value;
	}

}
