--秒杀执行的存储过程
DELIMITER $$ --console ;转换为$$
--定义存储过程
--参数：in 输入参数  out：输出参数
--row_count:返回上一条修改类型sql的影响行数
--row_count:0  未修改数据  >0:修改数据   <0:sql错误/未执行sql
create PROCEDURE `seckill`.`execute_seckill`
(in v_seckill_id bigint ,in v_phone bigint ,in v_kill_time timestamp,out r_result int )
 BEGIN
    DECLARE insert_count int DEFAULT 0;
    start TRANSACTION ;
    insert ignore into success_seckill
    (seckill_id,user_phone,create_time) VALUES (v_seckill_id,v_phone,v_kill_time);

    select ROW_COUNT() into insert_count;

    if(insert_count = 0) THEN
      ROLLBACK ;
      set r_result = -1;
    ELSEIF(insert_count<0) THEN
      ROLLBACK;
      set r_result=-2;
    else
      UPDATE seckill set number = number - 1 where seckill_id = v_seckill_id
      and end_time >v_kill_time
      and start_time < v_kill_time
      and number > 0;
      select row_count() into insert_count;
      if(insert_count = 0) THEN
        ROLLBACK ;
        set r_result = 0;
      ELSEIF(insert_count < 0) THEN
        ROLLBACK ;
        set r_result =-2;
      else
        COMMIT ;
        set r_result =1;
      end if;
    end if;
  end;
$$
--存储过程定义结束
DELIMITER ;
  set @r_result = -3;
  --执行存储过程
  call execute_seckill (1000,13503021151,now(),@r_result);
  --获取结果
  select @r_result;


