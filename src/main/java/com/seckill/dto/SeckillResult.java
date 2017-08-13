package com.seckill.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/7/7.
 */
//所有ajax请求返回类型,封装json结果
public class SeckillResult<T> {
    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private T data;
    @Getter
    @Setter
    private String error;

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
}
