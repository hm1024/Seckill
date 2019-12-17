package com.minghai.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description 用户
 * @date 2019/12/12
 */
@Data
public class SeckillUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
