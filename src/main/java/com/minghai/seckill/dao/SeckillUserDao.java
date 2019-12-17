package com.minghai.seckill.dao;

import com.minghai.seckill.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author minghai
 * @description 用户dao接口
 * @date 2019/12/12
 */
@Mapper
public interface SeckillUserDao {

    @Select("select * from seckill_user where id = #{id} ")
     SeckillUser getById(@Param("id") long id);
}
