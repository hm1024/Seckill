package com.minghai.seckill.controller;

import com.minghai.seckill.domain.OrderInfo;
import com.minghai.seckill.domain.SeckillOrder;
import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.result.CodeMsg;
import com.minghai.seckill.service.GoodsService;
import com.minghai.seckill.service.OrderService;
import com.minghai.seckill.service.SeckillService;
import com.minghai.seckill.service.SeckillUserService;
import com.minghai.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minghai
 * @description 秒杀Controller
 * @date 2020/3/31
 */
@Controller
@RequestMapping("/seckill/")
public class SeckillController {
    @Autowired
    private SeckillUserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("do_seckill")
    public String list(Model model, SeckillUser user,
                       @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return "login";
        }
        // 判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0){
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        // 判断是否已经秒杀到
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = seckillService.seckill(user,goods);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goods);

        return "order_detail";
    }
}
