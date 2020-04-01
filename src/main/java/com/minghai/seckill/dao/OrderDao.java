package com.minghai.seckill.dao;

import com.minghai.seckill.domain.OrderInfo;
import com.minghai.seckill.domain.SeckillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @author minghai
 * @description
 * @date 2020/3/31
 */
@Mapper
public interface OrderDao {

    @Select("select * from seckill_order where user_id = #{userId}  and goods_id = #{goodsId} ")
    public SeckillOrder getSeckillOrderByUserIdGoodId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id,goods_id,delivery_addr_id,goods_name,good_count,goods_price,order_channel,status,create_date) values (#{userId},#{goodsId} ,#{deliveryAddrId} ,#{goodsName} ,#{goodsCount} ,#{goodsPrice} ,#{orderChannel} ,#{status} ,#{createDate} )")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order(user_id,goods_id,order_id) values(#{userId} ,#{goodsId} ,#{orderId}) ")
    public void insetSeckillOrder(SeckillOrder seckillOrder);
}
