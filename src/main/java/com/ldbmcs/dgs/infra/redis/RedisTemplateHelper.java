package com.ldbmcs.dgs.infra.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.lang.NonNull;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

@Service
public class RedisTemplateHelper {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //--------------------------String start--------------------------------
    public <T> void set(String key, T value) {
        if (nonNull(value)) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public <T> void set(String key, T value, Duration ttl) {
        if (nonNull(value)) {
            redisTemplate.opsForValue().set(key, value, ttl);
        }
    }

    public <T> Boolean setNx(String key, T value, Duration ttl) {
        if (nonNull(value)) {
            return redisTemplate.opsForValue().setIfAbsent(key, value, ttl);
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(@NonNull String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }
    //--------------------------String end-----------------------------------

    /* Ops for Set */
    public Set<Object> getSetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void addToSet(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    public void removeFormSet(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    public Boolean isMemberOfSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /* Ops for Hash */

    public <T> T getHashValue(String hashKey, String valueKey) {
        BoundHashOperations<String, String, T> hashOperations = redisTemplate.boundHashOps(hashKey);
        return hashOperations.get(valueKey);
    }

    public <T> void setHashValue(String hashKey, String valueKey, T value) {
        BoundHashOperations<String, String, T> hashOperations = redisTemplate.boundHashOps(hashKey);
        hashOperations.put(valueKey, value);
    }

    public <T> void setHashValueIfAbsent(String hashKey, String valueKey, T value) {
        BoundHashOperations<String, String, T> hashOperations = redisTemplate.boundHashOps(hashKey);
        hashOperations.putIfAbsent(valueKey, value);
    }

    public List<Integer> getHashValuesByKeys(String hashKey, Collection<String> valueKeys) {
        BoundHashOperations<String, String, Integer> boundHashOperations = redisTemplate.boundHashOps(hashKey);
        return boundHashOperations.multiGet(valueKeys);
    }

    public <T> void setHashValues(String hashKey, Map<String, T> values) {
        BoundHashOperations<String, String, T> hashOperations = redisTemplate.boundHashOps(hashKey);
        hashOperations.putAll(values);
    }

    public <T> void setHashValues(String hashKey, Map<String, T> values, Duration ttl) {
        setHashValues(hashKey, values);
        redisTemplate.expire(hashKey, ttl);
    }

    public <T> Map<String, T> getHashValues(String hashKey) {
        BoundHashOperations<String, String, T> hashOperations = redisTemplate.boundHashOps(hashKey);
        return hashOperations.entries();
    }

    public <T> Map<String, T> multiHashGet(List<String> hashKeys, String field) {
        List<T> values = (List<T>) redisTemplate.executePipelined((RedisCallback<?>) connection -> {
            for (String hashKey : hashKeys) {
                connection.hGet(hashKey.getBytes(), field.getBytes());
            }
            return null;
        });
        HashMap<String, T> result = new HashMap<>();
        for (int i = 0; i < hashKeys.size(); i++) {
            result.put(hashKeys.get(i), values.get(i));
        }
        return result;
    }

    public Long incrementHashValue(String hashKey, String valueKey, Integer value) {
        BoundHashOperations<String, String, Long> hashOperations = redisTemplate.boundHashOps(hashKey);
        return hashOperations.increment(valueKey, value);
    }

    public Long decrementHashValue(String hashKey, String valueKey, Integer value) {
        BoundHashOperations<String, String, Long> hashOperations = redisTemplate.boundHashOps(hashKey);
        return hashOperations.increment(valueKey, value == 0 ? 0 : -value);
    }

    public Boolean hashValueExist(String hashKey, String valueKey) {
        return redisTemplate.boundHashOps(hashKey).hasKey(valueKey);
    }

    public void deleteHashKey(String hashKey, Object... valueKey) {
        redisTemplate.boundHashOps(hashKey).delete(valueKey);
    }

    public Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public <T> T execute(Class<T> resultType, String fileName, List<String> keys, Object... args) {
        DefaultRedisScript<T> luaScript = new DefaultRedisScript<>();
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/" + fileName)));
        luaScript.setResultType(resultType);
        return redisTemplate.execute(luaScript, keys, args);
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public void increment(String key, Long delta) {
        redisTemplate.opsForValue().increment(key, delta);
    }

    public void decrement(String key) {
        redisTemplate.opsForValue().decrement(key);
    }

    public Boolean exist(String key) {
        return redisTemplate.opsForValue().getOperations().hasKey(key);
    }

    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public Boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
}
