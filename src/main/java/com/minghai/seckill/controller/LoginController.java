package com.minghai.seckill.controller;

import com.alibaba.druid.util.StringUtils;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.result.ResponseVO;
import com.minghai.seckill.service.SeckillUserService;
import com.minghai.seckill.util.ValidateUtil;
import com.minghai.seckill.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseVO<Boolean>  doLogin(LoginVo loginVo){
        log.info(loginVo.toString());
        // 参数校验
        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if(StringUtils.isEmpty(passInput)){
            return ResponseVO.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(StringUtils.isEmpty(mobile)){
            return ResponseVO.error(CodeMsg.MOBILE_EMPTY);
        }
        if(!ValidateUtil.isMobile(mobile)){
            return ResponseVO.error(CodeMsg.MOBILE_ERROR);
        }
        // 登录
        CodeMsg cm = userService.login(loginVo);
        if(cm.getCode()  == 0){
            return ResponseVO.success(true);
        }else{
            return ResponseVO.error(cm);
        }
    }
}
