package com.seckill.dao;

import com.seckill.entity.Successkilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/6.
 */

/**
 * 配置spring和junit整合，junit启动时自动加载SpringIOC容器
 * Created by Administrator on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessSeckillDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilledTest() {
        long seckillId = 1001L;
        long userPhone = 12039238323L;
        int successnum = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println("successnum: " + successnum);
    }

    @Test
    public void queryByIdWirhSeckillTest() {
        long seckillId = 1001L;
        long userPhone = 12039238323L;
        Successkilled successkilled = successKilledDao.queryByIdWirhSeckill(seckillId,userPhone);
        System.out.println("queryByIdWirhSeckillTest: " + successkilled);
    }
}
