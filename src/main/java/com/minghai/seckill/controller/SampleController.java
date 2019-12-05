package com.minghai.seckill.controller;

import com.minghai.seckill.domain.User;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.result.ResponseVO;
import com.minghai.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghai
 * @description 控制层
 * @date 2019/12/3
 */
@Controller
@RequestMapping("/test/")
public class SampleController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("success")
    @ResponseBody
    public ResponseVO success(){
        return ResponseVO.success("hello success");
    }

    @RequestMapping("error")
    @ResponseBody
    public ResponseVO error(){
        return ResponseVO.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","minghai");
        return "hello";
    }
    @RequestMapping("db/get")
    @ResponseBody
    public ResponseVO<User> dbGet(){

        User user = userService.getById(1);

        return ResponseVO.success(user);
    }
    @RequestMapping("db/tx")
    @ResponseBody
    public ResponseVO<Boolean> dbTx(){

        boolean tx = userService.tx();

        return ResponseVO.success(tx);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public ResponseVO<Long> redisGet(){
        Long v1 = redisService.get("k1",Long.class);
        return ResponseVO.success(v1);
    }
    @RequestMapping("/redis/set")
    @ResponseBody
    public ResponseVO<String> redisSet(){
        boolean b = redisService.set("k2", "minghai");
        String k2 = redisService.get("k2", String.class);
        return ResponseVO.success(k2);
    }

}
