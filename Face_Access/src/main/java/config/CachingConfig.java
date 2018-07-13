package config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CachingConfig {
    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager cm) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<CacheManager> cacheManagers = new ArrayList<CacheManager>();
        cacheManagers.add(new EhCacheCacheManager(cm));
//        cacheManagers.add(new RedisCacheManager(redisTemplate));
        cacheManager.setCacheManagers(cacheManagers);
        return cacheManager;
    }

//    @Bean
//    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig poolConfig) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("127.0.0.1");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setPoolConfig(poolConfig);
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public JedisPoolConfig poolConfig() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.maxActive = 10;
//        poolConfig.maxIdle = 5;
//        poolConfig.minIdle = 1;
//        poolConfig.testOnBorrow = true;
//        poolConfig.testOnReturn = true;
//        poolConfig.testWhileIdle = true;
//        return poolConfig;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, String> redisTemplate =
//                new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean =
                new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(
                new ClassPathResource("ehcache.xml"));
        return ehCacheFactoryBean;
    }
}
