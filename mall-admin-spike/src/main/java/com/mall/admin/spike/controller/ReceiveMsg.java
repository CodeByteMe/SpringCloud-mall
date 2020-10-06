package com.mall.admin.spike.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ReceiveMsg
 *
 * @Author BessCroft
 * @Date 2020/10/4 13:52
 */
@Component
public class ReceiveMsg {

    @RabbitListener(queues = "queue1")
    public void process(String msg){

        System.out.println("Receiver : " + msg);
    }

}
