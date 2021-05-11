package com.luna.rabbitmq.customerservice;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author luna@mac
 * 2021年04月20日 13:03
 */
@Component
public class RabbitCustomer {

    @RabbitListener(queuesToDeclare = @Queue(value = "${luna.queue.basic}", durable = "true", autoDelete = "true"))
    public void baseReceive(String message) {
        System.out.println("message-1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "${luna.queue.worker}"))
    public void workQueueOne(String message) {
        System.out.println("message-worker-1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "${luna.queue.worker}"))
    public void workQueueTwo(String message) {
        System.out.println("message-worker-2 = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(name = "${luna.exchange.fanout}", type = "fanout")))
    public void fanoutOne(String message) {
        System.out.println("message-fanout-1 = " + message);
    }

    /**
     * 创建临时队列
     * 绑定交换机类型
     * 
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(name = "${luna.exchange.fanout}", type = "fanout")))
    public void fanoutTwo(String message) {
        System.out.println("message-fanout-2 = " + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue(),
            key = {"info", "error"},
            exchange = @Exchange(type = "direct", name = "${luna.exchange.direct}"))})
    public void directOne(String message) {
        System.out.println("message-direct-1 = " + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue(),
            key = {"error"},
            exchange = @Exchange(type = "direct", name = "${luna.exchange.direct}"))})
    public void directTwo(String message) {
        System.out.println("message-direct-2 = " + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,
            key = {"user.*"},
            exchange = @Exchange(type = "topic", name = "${luna.exchange.topic}"))
    })
    public void topicOne(String message) {
        System.out.println("message-topic-1 = " + message);
    }

    @RabbitListener(bindings = {
        @QueueBinding(
            value = @Queue,
            key = {"user.#"},
            exchange = @Exchange(type = "topic", name = "${luna.exchange.topic}"))
    })
    public void topicTwo(String message) {
        System.out.println("message-topic-2 = " + message);
    }
}