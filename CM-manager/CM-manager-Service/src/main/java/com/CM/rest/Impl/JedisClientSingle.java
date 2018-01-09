package com.CM.rest.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.CM.component.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient
{
	@Autowired
	private JedisPool jedisPool;
	@Override
	public String set(String key, String value)
	{
		Jedis jedis = jedisPool.getResource();
		value = jedis.set(key, value);
		return value;
	}

	@Override
	public String get(String key)
	{
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		return value;
	}

	@Override
	public Long hset(String key, String item, String value)
	{
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, item, value);
		return result;
	}

	@Override
	public String hget(String key, String item)
	{
		Jedis jedis = jedisPool.getResource();
		String value = jedis.hget(key, item);
		return value;
	}

	@Override
	public Long incr(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.incr(key);
		return value;
	}

	@Override
	public Long decr(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.decr(key);
		return value;
	}

	@Override
	public Long expire(String key, int second)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.expire(key, second);
		return value;
	}

	@Override
	public Long ttl(String key)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.ttl(key);
		return value;
	}

	@Override
	public Long hdel(String key, String item)
	{
		Jedis jedis = jedisPool.getResource();
		Long value = jedis.hdel(key, item);
		return value;
	}

}
