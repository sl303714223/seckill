package com.seckill.dto;

import com.seckill.entity.Successkilled;
import com.seckill.enums.SeckillStatEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装秒杀执行后的结果
 * Created by Administrator on 2017/7/6.
 */
@ToString
public class SeckillExecution {
    @Getter
    @Setter
    private long seckillId;

    //秒杀执行结果状态
    @Getter
    @Setter
    private int state;
    //状态标识
    @Getter
    @Setter
    private String stateInfo;
    //秒杀成功对象
    @Getter
    @Setter
    private Successkilled successkilled;

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, Successkilled successkilled) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.successkilled = successkilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }
}
