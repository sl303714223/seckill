package com.seckill.exception;

/**
 * 秒杀成功异常
 * Created by Administrator on 2017/7/6.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
