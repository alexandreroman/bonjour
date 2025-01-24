package com.broadcom.tanzu.demos.bonjour;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;

@Service
class PageViewService {
    private final RedisConnectionFactory connectionFactory;
    private final MeterRegistry meterRegistry;

    PageViewService(RedisConnectionFactory connectionFactory, MeterRegistry meterRegistry) {
        this.connectionFactory = connectionFactory;
        this.meterRegistry = meterRegistry;
    }

    int incrementPageViewCount(String page) {
        meterRegistry.counter("bonjour.pageviews", "page", page).increment();
        return new RedisAtomicInteger("views::" + page, connectionFactory).incrementAndGet();
    }
}
