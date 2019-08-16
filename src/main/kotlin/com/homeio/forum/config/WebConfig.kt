package com.homeio.forum.config

import org.ehcache.core.EhcacheManager
import org.springframework.cache.CacheManager

import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.jcache.JCacheCacheManager

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.cache.Caching

@Configuration
@EnableCaching
class WebConfig: WebMvcConfigurer{

    @Bean
    fun cacheManager(): CacheManager{
       return JCacheCacheManager(Caching.getCachingProvider().getCacheManager(
                WebMvcConfigurer::class.java.getResource("/env/ehcache.xml").toURI(),
                WebMvcConfigurer::class.java.getClassLoader()))
    }

}