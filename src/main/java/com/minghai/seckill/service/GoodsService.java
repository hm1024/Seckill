package com.minghai.seckill.service;

import com.minghai.seckill.dao.GoodsDao;
import com.minghai.seckill.domain.Goods;
import com.minghai.seckill.domain.SeckillGoods;
import com.minghai.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/27
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return  goodsDao.listGoodsVO();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void resuceStock(GoodsVo goods) {
        SeckillGoods g = new SeckillGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);

    }
}
