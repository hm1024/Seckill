package com.minghai.seckill.service;

import com.minghai.seckill.dao.SeckillUserDao;
import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.exection.GlobalException;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.redis.SeckillUserKey;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.util.MD5Util;
import com.minghai.seckill.util.UUIDUtil;
import com.minghai.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author minghai
 * @description
 * @date 2019/12/12
 */
@Service
public class SeckillUserService {


    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private SeckillUserDao seckillUserDao;

    @Autowired
    private RedisService redisService;

    public SeckillUser getById(long id){
        return seckillUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null ){
            throw new GlobalException( CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        // 判断手机号是否已存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException( CodeMsg.MOBILE_NOT_EXISTS);
        }

        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);


        if(!dbPass.equals(calcPass)){
            throw new GlobalException( CodeMsg.PASSWORD_ERROR);
        }

        // 生成cokie
        addCookie(user,response);
        return true;


    }

    public SeckillUser getByToken(HttpServletResponse respnose,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        SeckillUser user =  redisService.get(SeckillUserKey.token,token,SeckillUser.class);
        // 延长有效期
        if(user != null){
            addCookie(user,respnose);
        }
        return user;
    }

    private void addCookie(SeckillUser user,HttpServletResponse response){
        String token = UUIDUtil.uuid();
        redisService.set(SeckillUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
