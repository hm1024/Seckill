package com.minghai.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/27
 */
@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    // 给Controller赋值SeckillUser
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }
}
