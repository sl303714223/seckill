package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface SeckillDao {
    /**
     * 减库存
     *
     * @param seckillId 对应的库存id
     * @param KillTime  秒杀时间
     * @return 被减少的数量
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("KillTime") Date KillTime);

    /**
     * 根据ID查询秒杀对象
     *
     * @param seckillId 对象id
     * @return 如果影响行数>1,则表示购买数量
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品集合
     *
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryList(@Param("offset") int offet, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paraMap
     */
    void killByProcedure(Map<String, Object> paraMap);
}
