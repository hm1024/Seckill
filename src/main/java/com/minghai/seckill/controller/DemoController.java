package com.minghai.seckill.controller;

import com.minghai.seckill.domain.User;
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
public class DemoController {


    @Autowired
    UserService userService;

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

}
