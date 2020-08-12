package br.com.api.infrastructure.cache;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultCache {
    public static final String DEFAULT_CACHE_NAME = "default";

    @Value("${app.cache.default.cache.time-to-live-seconds}")
    private Long CACHE_TIME_TO_LIVE;

    @Value("${app.cache.default.cache.max-idle-seconds}")
    private Long CACHE_MAX_IDLE;

    @Bean(name = DEFAULT_CACHE_NAME)
    public CacheConfiguration defaultCache(){
        CacheConfiguration defaultCache = new CacheConfiguration();
        defaultCache.setMemoryStoreEvictionPolicy("LFU");
        defaultCache.overflowToOffHeap(false);
        defaultCache.eternal(false);
        defaultCache.timeToIdleSeconds(CACHE_MAX_IDLE);
        defaultCache.timeToLiveSeconds(CACHE_TIME_TO_LIVE);
        defaultCache.transactionalMode("off");
        defaultCache.persistence(new PersistenceConfiguration().strategy("none"));

        return defaultCache;
    }

}
