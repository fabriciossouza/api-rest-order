package br.com.api.infrastructure.cache;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortTermCache {

    public static final String SHORT_TERM_CACHE_NAME = "short-term-cache";

    @Value("${app.cache.short.cache.time-to-live-seconds}")
    private Long CACHE_TIME_TO_LIVE;

    @Value("${app.cache.short.cache.max-idle-seconds}")
    private Long CACHE_MAX_IDLE;

    @Value("${app.cache.short.cache.used-heap-size}")
    private String CACHE_USED_HEAP_SIZE;

    @Bean(name = SHORT_TERM_CACHE_NAME )
    public CacheConfiguration shortTermCache(){
        CacheConfiguration shortTermCache = new CacheConfiguration();
        shortTermCache.name(SHORT_TERM_CACHE_NAME );
        shortTermCache.setMemoryStoreEvictionPolicy("LFU");
        shortTermCache.overflowToOffHeap(false);
        shortTermCache.eternal(false);
        shortTermCache.timeToIdleSeconds(CACHE_MAX_IDLE);
        shortTermCache.timeToLiveSeconds(CACHE_TIME_TO_LIVE);
        shortTermCache.transactionalMode("off");
        shortTermCache.setMaxBytesLocalHeap(CACHE_USED_HEAP_SIZE);

        return shortTermCache;
    }
}
