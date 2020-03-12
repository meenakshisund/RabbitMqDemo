package com.rabbit.test.RabbitMqDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    private Producer producer;

    @GetMapping("/{msg}")
    public String sendMessage(@PathVariable("msg")String message){
        producer.send(message);
        return "Received Message : " + message;
    }
}
