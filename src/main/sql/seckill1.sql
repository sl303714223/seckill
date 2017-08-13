-- 创建seckill表

create table seckill1 (
  seckill_id bigint not null AUTO_INCREMENT COMMENT '商品库存id',
  name varchar(20) not null COMMENT '商品名称',
  number int not null COMMENT '商品余量',
  create_time TIMESTAMP not null DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  start_time TIMESTAMP  not null COMMENT '开始时间',
  end_time TIMESTAMP  not null COMMENT '结束时间',
  PRIMARY  KEY (seckill_id),
  KEY idx_start_time (start_time),
  key idx_end_time (end_time),
  KEY idx_create_time(create_time)
)ENGINE InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT '秒杀库存表'