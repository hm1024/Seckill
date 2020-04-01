package com.minghai.seckill.controller;

import com.minghai.seckill.domain.SeckillUser;
import com.minghai.seckill.redis.RedisService;
import com.minghai.seckill.service.GoodsService;
import com.minghai.seckill.service.SeckillUserService;
import com.minghai.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/25
 */
@Controller
@RequestMapping("/goods/")
@Slf4j
public class GoodsControler {

    @Autowired
    private SeckillUserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("to_list")
    public String toList(Model model,SeckillUser user){
        model.addAttribute("user",user);

        // 查询商品列表
        List<GoodsVo> goodsVo =   goodsService.listGoodsVo();
        log.info(goodsVo.toString());
        model.addAttribute("goodsList",goodsVo);

        return "goods_list";
    }
    @RequestMapping("to_detail/{goodsId}")
    public String toDetail(Model model, SeckillUser user,
                           @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);

        //
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if(now < startAt) { // 秒杀没有开始,倒计时
            seckillStatus = 0;
            remainSeconds = (int)(startAt - now)/1000; // 毫秒转换秒
        }else if(now > endAt){ // 秒杀已结束
            seckillStatus = 2;
            remainSeconds = -1;
        }else{ //秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);

        return "goods_detail";
    }


}
