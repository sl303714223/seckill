package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"classpath:spring/spring-service.xml",
                "classpath:spring/spring-dao.xml"}
)
public class SeckillServiceTest {
    @Autowired
    private SeckillService seckillService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getSeckillListTest() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getByIdTest() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillService.getById(seckillId);
        logger.info("secilll={}", seckill);
    }

    @Test
    //测试代码完整逻辑，注意可重复执行
    /*
    Exposer(exposed=true, md5=1ea51b34c79c5f0853a297e30f951919, seckillId=1000, now=0, start=0, end=0)
     */
    public void exportSeckillLogicTest() throws Exception {
        long seckillId = 1001L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long userPhone = 13029382730L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            } catch (SeckillCloseException e1) {
                logger.error(e1.getMessage(), e1);
            } catch (RepeatKillException e2) {
                logger.error(e2.getMessage(), e2);
            } catch (SeckillException e3) {
                logger.error(e3.getMessage(), e3);
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}" + exposer);
        }
    }

    @Test
    public void executeSeckillTest() throws Exception {
        long seckillId = 1000L;
        long userPhone = 13029382730L;
        String md5 = "1ea51b34c79c5f0853a297e30f951919";
        try {

        } catch (SeckillCloseException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (RepeatKillException e2) {
            logger.error(e2.getMessage(), e2);
        } catch (SeckillException e3) {
            logger.error(e3.getMessage(), e3);
        }
        SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
        logger.info("seckillExecution={}", seckillExecution);
    }

    @Test
    public void executeSeckillProcedureTest() {
        long seckillId = 1000L;
        long phone = 12323232323L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            System.out.println(execution.getStateInfo());
        }

    }
}
