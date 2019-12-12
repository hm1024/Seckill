package com.minghai.seckill.redis;

/**
 * @author minghai
 * @description
 * @date 2019/12/12
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
