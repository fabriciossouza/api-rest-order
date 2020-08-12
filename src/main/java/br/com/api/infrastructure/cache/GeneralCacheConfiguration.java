package br.com.api.infrastructure.cache;

import net.sf.ehcache.config.MemoryUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralCacheConfiguration {

    @Value("${app.cache.max.bytes.local.heap}")
    private Long CACHE_MAX_BYTES_LOCAL_HEAP;

    @Bean
    public net.sf.ehcache.config.Configuration ehCacheConfiguration() {
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();

        config.monitoring(net.sf.ehcache.config.Configuration.Monitoring.AUTODETECT);
        config.maxBytesLocalHeap(CACHE_MAX_BYTES_LOCAL_HEAP, MemoryUnit.MEGABYTES);
        config.updateCheck(true);
        config.dynamicConfig(false);
        return config;
    }
}
