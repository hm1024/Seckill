package com.minghai.seckill.service;

import com.minghai.seckill.dao.SeckillUserDao;
import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.exection.GlobalException;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.util.MD5Util;
import com.minghai.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author minghai
 * @description
 * @date 2019/12/12
 */
@Service
public class SeckillUserService {

    @Autowired
    private SeckillUserDao seckillUserDao;

    public SeckillUser getById(long id){
        return seckillUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo) {
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
        return true;


    }
}
