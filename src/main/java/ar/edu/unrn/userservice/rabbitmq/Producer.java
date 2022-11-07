package ar.edu.unrn.userservice.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

public class Producer {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(RabbitMqConfig.class);
    RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);

    public void sendMessage(String msj){
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, msj));
    }
}
