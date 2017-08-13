package com.seckill.entity;

/**
 * Created by Administrator on 2017/7/5.
 */

import lombok.*;

import java.util.Date;

/**
 * 秒杀库存实体
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seckill {
    @Getter
    @Setter
    private long seckillId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int number;
    @Getter
    @Setter
    private Date createtime;
    @Getter
    @Setter
    private Date starttime;
    @Getter
    @Setter
    private Date endtime;
}
