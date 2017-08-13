package com.seckill.entity;

import lombok.*;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/5.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Successkilled {
    @Getter
    @Setter
    private long seckillId;
    @Getter
    @Setter
    private long userPhone;
    @Getter
    @Setter
    private short state;
    @Getter
    @Setter
    private Date createTime;
    @Getter
    @Setter
    private Seckill seckill;
}
