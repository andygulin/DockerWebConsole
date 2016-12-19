package docker.web.console.service.impl;

import docker.web.console.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    private final StringRedisSerializer serializer = new StringRedisSerializer();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(final String key, final String value) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return null;
        });
    }

    @Override
    public void setEx(final String key, final String value, final long expire) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.setEx(serializer.serialize(key), expire, serializer.serialize(value));
            return null;
        });
    }

    @Override
    public String get(final String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> serializer.deserialize(connection.get(serializer.serialize(key))));
    }

    @Override
    public long del(final String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(serializer.serialize(key)));
    }

    @Override
    public boolean exist(final String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(serializer.serialize(key)));
    }

    @Override
    public Set<String> keys(final String pattern) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<byte[]> bSet = connection.keys(serializer.serialize(pattern));
            Set<String> set = new LinkedHashSet<>(bSet.size());
            for (byte[] b : bSet) {
                set.add(serializer.deserialize(b));
            }
            return set;
        });
    }
}
