package com.example.forumbackend.Utils;

import lombok.Setter;
import org.apache.ibatis.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/3/6
 */
@Component
public class MybatisRedisCache implements Cache {

    private RedisUtil redisUtil;

    private RedisUtil getRedis(){
        return SpringContextUtils.getBean(RedisUtil.class);
    }

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 缓存刷新时间（秒）
     */
    @Setter
    private long flushInterval = 0L;

    private String id;

    public MybatisRedisCache() {}

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        getRedis().hset(getId(), o.toString(), o1);

        if (flushInterval > 0L) {
            getRedis().expire(getId(), flushInterval);
        }
    }

    @Override
    public Object getObject(Object o) {
        return getRedis().hget(getId(), o.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return getRedis().hdel(getId(), o);
    }

    @Override
    public void clear() {
        getRedis().del(getId());
    }

    @Override
    public int getSize() {
        return getRedis().hsize(getId());
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}