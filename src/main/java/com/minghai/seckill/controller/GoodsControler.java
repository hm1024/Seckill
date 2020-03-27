package com.minghai.seckill.controller;

import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.service.SeckillUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author minghai
 * @description
 * @date 2020/3/25
 */
@Controller
@RequestMapping("/goods/")
@Slf4j
public class GoodsControler {

    @Autowired
    private SeckillUserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("to_list")
    public String toList(HttpServletResponse response,Model model,SeckillUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}
