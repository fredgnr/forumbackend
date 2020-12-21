package com.example.forumbackend.Utils.cacheUtils;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/3/6
 */
@Component
public class MybatisRedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    /**
     * 缓存刷新时间（秒）
     */
    @Setter
    private long flushInterval = 0L;

    @Getter
    private String id;

    public MybatisRedisCache() {}

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }


    @Override
    public void putObject(Object o, Object o1) {
       SpringContextUtils.getBean(RedisUtil.class).hset(getId(), o.toString(), o1);
        if (flushInterval > 0L) {
            SpringContextUtils.getBean(RedisUtil.class).expire(getId(), flushInterval);
        }
    }

    @Override
    public Object getObject(Object o) {
        return SpringContextUtils.getBean(RedisUtil.class).hget(getId(), o.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return SpringContextUtils.getBean(RedisUtil.class).hdel(getId(), o);
    }

    @Override
    public void clear() {
        SpringContextUtils.getBean(RedisUtil.class).del(getId());
    }

    @Override
    public int getSize() {
        return SpringContextUtils.getBean(RedisUtil.class).hsize(getId());
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}