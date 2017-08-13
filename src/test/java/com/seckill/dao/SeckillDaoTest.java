package com.seckill.dao;

import com.seckill.entity.Seckill;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置spring和junit整合，junit启动时自动加载SpringIOC容器
 * Created by Administrator on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入DAO实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int num = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("num : " + num);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        int offset = 0;
        int limit = 3;
        List<Seckill> list = seckillDao.queryList(offset, limit);
        System.out.println("size--------" + list.size());
        for (Seckill s : list) {
            System.out.println(s.getName());
            System.out.println(s);
        }
    }

}
