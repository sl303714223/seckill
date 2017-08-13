//存放交互逻辑
//javascript 模块化
var seckill = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }

    },
    //验证手机号
    validatePhone: function (phone) {

        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    handleSeckillkill: function (seckillId, node) {
        //获取秒杀地址，控制显示逻辑，执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(
            seckill.URL.exposer(seckillId), {}, function (result) {
                //在回调函数中执行交互流程
                if (result && result['success']) {
                    var exposer = result['data'];
                    if (exposer['exposed']) {
                        //开启秒杀
                        //获取秒杀地址
                        var md5 = exposer['md5'];
                        var killUrl = seckill.URL.execution(seckillId, md5);
                        //绑定一次点击事件
                        $('#killBtn').one('click', function () {
                            //执行秒杀请求
                            //1)现禁用按钮
                            $(this).addClass('disabled');
                            //2)发送秒杀请求
                            $.post(killUrl, {}, function (result) {
                                //发送请求，执行秒杀
                                if (result && result['success']) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    //显示秒杀结果
                                    node.html('<span class="label label-success">' + stateInfo + '</span>');
                                }
                            });
                        });
                        node.show();
                    } else {
                        //未开启秒杀
                        var now = exposer['now'];
                        var start = exposer['start'];
                        var end = exposer['end'];
                        seckill.countdown(seckillId, now, start, end);
                    }
                } else {

                }
            }
        )
        ;

    },
    countdown: function (seckillId, nowtime, starttime, endtime) {
        var seckillBox = $('#glyphicon-box');

        if (nowtime > endtime) {
            seckillBox.html('秒杀结束!');
        } else if (nowtime <= starttime) {

            //秒杀未开始,计时时间绑定
            var killtime = new Date(starttime + 1000);
            seckillBox.countdown(killtime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {

        //详情页初始化
        init: function (params) {

            //用户手机验证和登录，计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定手机号
                //控制输出
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                        show: true,//显示弹出层
                        backdrop: 'static',//禁止位置关闭
                        keyboard: false//关闭键盘事件
                    }
                );
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhone').val();
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<lable class="lable lable-danger">手机号错误！</lable></lable>').show(300);
                    }
                });
            }


            //已经登录
            //计时交互
            var starttime = params['starttime'];
            var endtime = params['endtime'];
            var seckillId = params['seckillId'];

            $.get(seckill.URL.now(), function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId, nowTime, starttime, endtime);
                } else {

                }
            });
        }
    }
}