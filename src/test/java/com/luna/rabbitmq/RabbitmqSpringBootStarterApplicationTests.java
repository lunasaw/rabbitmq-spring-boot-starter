package com.luna.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
class RabbitmqSpringBootStarterApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }



}
