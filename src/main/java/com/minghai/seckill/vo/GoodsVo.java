package com.minghai.seckill.vo;

import com.minghai.seckill.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/3/30
 */
@Data
public class GoodsVo extends Goods {
    private Double seckillPrice;
    // 秒杀库存
    private Date startDate;
    private Integer stockCount;
    private Date endDate;
}
