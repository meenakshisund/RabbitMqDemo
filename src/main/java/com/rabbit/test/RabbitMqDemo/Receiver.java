package com.rabbit.test.RabbitMqDemo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class Receiver {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessage(String message){
        System.out.println("getting message " + message);
    }
}