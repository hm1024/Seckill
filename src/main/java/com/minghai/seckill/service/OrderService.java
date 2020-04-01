package com.minghai.seckill.service;

import com.minghai.seckill.dao.GoodsDao;
import com.minghai.seckill.dao.OrderDao;
import com.minghai.seckill.domain.OrderInfo;
import com.minghai.seckill.domain.SeckillOrder;
import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.vo.GoodsVo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/27
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {

        return orderDao.getSeckillOrderByUserIdGoodId(userId,goodsId);
    }

    @Transactional
    public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);



        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setGoodsId(goods.getId());
        orderDao.insetSeckillOrder(seckillOrder);

        return orderInfo;
    }
}
