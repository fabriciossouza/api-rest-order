package br.com.api.infrastructure.cache;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

import static br.com.api.infrastructure.cache.DefaultCache.DEFAULT_CACHE_NAME;
import static br.com.api.infrastructure.cache.ShortTermCache.SHORT_TERM_CACHE_NAME;

@Configuration
@EnableCaching
public class AppCacheConfiguration implements CachingConfigurer {

    private CacheConfiguration defaultCache;
    private CacheConfiguration shortTermCache;
    private net.sf.ehcache.config.Configuration ehCacheConfiguration;
    public static final String DEFAULT_KEY_GENERATOR = "keyGenerator";

    @Autowired
    public AppCacheConfiguration(@Qualifier(DEFAULT_CACHE_NAME)   CacheConfiguration defaultCache,
                                 @Qualifier(SHORT_TERM_CACHE_NAME) CacheConfiguration shortTermCache,
                                 net.sf.ehcache.config.Configuration ehCacheConfiguration) {
        this.defaultCache = defaultCache;
        this.shortTermCache = shortTermCache;
        this.ehCacheConfiguration = ehCacheConfiguration;
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        ehCacheConfiguration.defaultCache(defaultCache);
        ehCacheConfiguration.addCache(shortTermCache);
        return net.sf.ehcache.CacheManager.newInstance(ehCacheConfiguration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... params) {
                return new SimpleKey( o.getClass().getName(), method.getName(), params);
            }
        };
    }

    @Bean
    @Override
    public CacheResolver cacheResolver()    {
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }
}