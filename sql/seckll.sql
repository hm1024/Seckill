CREATE TABLE `seckill_user` (
`id` BIGINT ( 20 ) NOT NULL COMMENT '用户ID,手机号',
`nickname` VARCHAR ( 255 ) NOT NULL,
`password` VARCHAR ( 32 ) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt) + salt)',
`salt` VARCHAR ( 10 ) DEFAULT NULL,
`head` VARCHAR ( 128 ) DEFAULT NULL COMMENT '头像，云存储时的ID',
`register_date` datetime DEFAULT NULL COMMENT '注册时间',
`last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
`login_count` INT ( 11 ) DEFAULT '0' COMMENT '上次登录时间',
PRIMARY KEY ( `id` )
) ENGINE = INNODB DEFAULT charset = utf8mb4;

-- 商品表
CREATE TABLE `goods`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
    `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
    `goods_img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
    `goods_detail` longtext COMMENT '商品的详情介绍',
    `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
    `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存， -1表示没有限制',
    PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO `goods` VALUES (1,'iphoneX','Apple Iphone X(A1865) 64G 银色移动联通电信4G','/img/iphonex.png','Apple Iphone X(A1865) 64G 银色移动联通电信4G收集',8765.00,10000),(2,'HUAWEI Mate20','HUAWEI Mate20 (A1865) 64G 银色移动联通电信4G','/img/mate20.png','HUAWEI Mate20(A1865) 64G 银色移动联通电信4G收集',3212.00,-1)

-- 秒杀商品表
CREATE TABLE `seckill_goods`(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品表id',
    `goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
    `seckill_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '秒杀价格',
    `stock_count` INT(11) DEFAULT NULL COMMENT '秒杀库存',
    `start_date` DATETIME DEFAULT NULL COMMENT '秒杀开始时间',
    `end_date` DATETIME DEFAULT NULL COMMENT '秒杀结束时间',
    PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO `seckill_goods` values (1,1,0.01,4,'2020-3-27 22:00:00','2020-4-27 22:00:00');

-- 订单表
CREATE TABLE `order_info`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
    `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址ID',
    `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过后的商品名称',
    `good_count` int(11) DEFAULT '0' COMMENT '商品数量',
    `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品价格',
    `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc,2android,3 ios',
    `status` tinyint(4) DEFAULT '0' COMMENT '订单状态，0新建支付，1已支付，2已发货，3已收货，4已退款，5已完成',
    `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
    `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seckill_order`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAUlt  NULL COMMENT '用户ID',
    `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
    `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
    PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;