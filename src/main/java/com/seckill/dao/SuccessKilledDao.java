package com.seckill.dao;

import com.seckill.entity.Successkilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据Id查询Successkilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @return
     */
    Successkilled queryByIdWirhSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
