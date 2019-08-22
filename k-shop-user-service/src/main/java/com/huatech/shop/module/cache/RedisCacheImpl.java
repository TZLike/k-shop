package com.huatech.shop.module.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author leek
 * @Date 2018-10-23 下午1:51
 * @Version 1.0
 * @Description
 */
@Service
public class RedisCacheImpl implements ICacheService {
    @Autowired
    RedisTemplate<Serializable, Object> redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        T value = (T) redisTemplate.opsForValue().get(key);
        return value;
    }

    @Override
    public <T> T get(String key, ICacheService.ICallback callback) {
        T value = get(key);
        if (value == null) {
            value = callback.execute();
            set(key, value);
        }
        return value;
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long expires) {
        redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}
