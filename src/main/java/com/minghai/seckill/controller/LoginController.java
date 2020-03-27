package com.minghai.seckill.controller;

import com.alibaba.druid.util.StringUtils;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.result.ResponseVO;
import com.minghai.seckill.service.SeckillUserService;
import com.minghai.seckill.util.ValidatorUtil;
import com.minghai.seckill.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author minghai
 * @description
 * @date 2019/12/12
 */
@Controller
@RequestMapping("/login/")
@Slf4j
public class LoginController {

    @Autowired
    private SeckillUserService userService;

    @RequestMapping("to_login")
    public String toLogin(){
        log.info("toLogin");
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public ResponseVO<Boolean>  doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        // 登录
        userService.login(response,loginVo);
        return ResponseVO.success(true);
    }
}
