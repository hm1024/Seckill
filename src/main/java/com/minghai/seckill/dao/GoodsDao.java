package com.minghai.seckill.dao;

import com.minghai.seckill.domain.Goods;
import com.minghai.seckill.domain.SeckillGoods;
import com.minghai.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/3/27
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*,sg.stock_count,sg.start_date,sg.end_date,sg.seckill_price from seckill_goods sg left join goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVO();

    @Select("select g.*,sg.stock_count,sg.start_date,sg.end_date,sg.seckill_price from seckill_goods sg left join goods g on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update seckill_goods set stock_count = stock_count -1 where goods_id = #{goodsId}")
    public void reduceStock(SeckillGoods id);
}
