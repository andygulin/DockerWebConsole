package docker.web.console.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import docker.web.console.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private StringRedisSerializer serializer = new StringRedisSerializer();

	@Override
	public void set(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return null;
			}
		});
	}

	@Override
	public void setEx(final String key, final String value, final long expire) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(serializer.serialize(key), expire, serializer.serialize(value));
				return null;
			}
		});
	}

	@Override
	public String get(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return serializer.deserialize(connection.get(serializer.serialize(key)));
			}
		});
	}

	@Override
	public long del(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(serializer.serialize(key));
			}
		});
	}

	@Override
	public boolean exist(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(serializer.serialize(key));
			}
		});
	}

	@Override
	public Set<String> keys(final String pattern) {
		return redisTemplate.execute(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
				Set<byte[]> bSet = connection.keys(serializer.serialize(pattern));
				Set<String> set = new LinkedHashSet<>(bSet.size());
				for (byte[] b : bSet) {
					set.add(serializer.deserialize(b));
				}
				return set;
			}
		});
	}
}
