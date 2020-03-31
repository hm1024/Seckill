package com.minghai.seckill.controller;

import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.service.GoodsService;
import com.minghai.seckill.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author minghai
 * @description 秒杀Controller
 * @date 2020/3/31
 */
@Controller
public class SeckillController {
    @Autowired
    private SeckillUserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;
}
