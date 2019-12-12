package com.minghai.seckill.redis;

import com.minghai.seckill.domain.User;

/**
 * @author minghai
 * @description
 * @date 2019/12/12
 */
public class UserKey extends  BasePrefix{
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("id");
}
