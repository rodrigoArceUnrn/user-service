package ar.edu.unrn.userservice.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Producer.
 */
@Component
@EnableRabbit
public class Producer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Queue queue;

  public void send(String message) {
    rabbitTemplate.convertAndSend(queue.getName(), message);
  }
}