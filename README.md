RabbitMqDemo 

Reference article: https://www.candidjava.com/tutorial/spring-boot-rabbitmq-example-using-maven/

docker-compose up to up RabbitMq.

**Dependency:**
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```
**RabbitConfig.java**
    - has Queue, DirectExchange and Binding type beans.
Queue Bean:
```
@Bean
Queue queueBean(){
    return new Queue(queueName);
}
``` 

AbstractExchange type (DirectExchange, FanoutExchange etc):
```
@Bean
DirectExchange exchangeBean(){
    return new DirectExchange(excahngeName);
}
```

Binding bean:
```
@Bean
Binding binding(){
    Binding.bind(queueBean()).to(exchangeBean()).with(routingKey);
}
```
MesssageConvertor Bean:  
```
@Bean
MessageConvertor jsonMessageConverter(){
    return new Jackson2JsonMessageConvertor();
}
```

RabbitTemplate Bean of type AmqpTemplate:
1. Use RabbitTemplate constructor that accepts ConnectionFactory
2. setMessageConvertor as the above created jsonMessageConvertor() Bean type
```
@Bean
RabbitTemplate rabbitTemplate(){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
}
```

**Producer.java**
    - has exchange, routingKey values and rabbitTemplate Bean to call 
```amqpTemplate.convertAndSend(exchange, routingKey, message);'```

**Receiver.java**
    - need to listen to the Queue with @RabbitListener(queues="queueName")
```
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getMessage(String message){
        System.out.println("getting message " + message);
    }
```

Controller:

Call the send(msg) method in Producer to ingest data into the exchange with routingKey.