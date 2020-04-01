package com.minghai.seckill.service;

import com.minghai.seckill.dao.GoodsDao;
import com.minghai.seckill.domain.Goods;
import com.minghai.seckill.domain.OrderInfo;
import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author minghai
 * @description
 * @date 2020/3/31
 */
@Service
@Slf4j
public class SeckillService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo seckill(SeckillUser user, GoodsVo goods) {
        // 减库存 下订单 写入秒杀订单
        goodsService.resuceStock(goods);
        log.info("减库存成功");
        // order_info  seckill_order
        return orderService.createOrder(user,goods);
    }
}
