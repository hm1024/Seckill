package com.minghai.seckill.controller;

import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.result.ResponseVO;
import com.minghai.seckill.service.GoodsService;
import com.minghai.seckill.service.SeckillUserService;
import com.minghai.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/25
 */
@Controller
@RequestMapping("/user/")
@Slf4j
public class UserControler {

    @Autowired
    private SeckillUserService userService;



    @RequestMapping("info")
    @ResponseBody
    public ResponseVO<SeckillUser> toList(Model model, SeckillUser user){

        return ResponseVO.success(user);
    }


}
