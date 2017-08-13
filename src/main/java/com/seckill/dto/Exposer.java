package com.seckill.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 暴露秒杀地址DTO
 * Created by Administrator on 2017/7/6.
 */
@ToString
public class Exposer {
    //秒杀地址是否可以暴露
    @Getter
    @Setter
    private boolean exposed;
    @Getter
    @Setter
    //一种加密措施
    private String md5;
    @Getter
    @Setter
    private long seckillId;
    @Getter
    @Setter
//系统时间
    private long now;
    //开启时间
    @Getter
    @Setter
    private long start;
    //结束时间
    @Getter
    @Setter
    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long now, long start, long end, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }


}
