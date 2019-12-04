package com.minghai.seckill.service;

import com.minghai.seckill.dao.UserDao;
import com.minghai.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minghai
 * @description 用户service
 * @date 2019/12/3
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

//    @Transactional
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("222");
        userDao.insert(u1);

        User u2 = new User();
        u2.setId(1);
        u2.setName("111");
        userDao.insert(u2);

        return true;
    }
}
