package ar.edu.unrn.userservice.domain.impl;

import ar.edu.unrn.userservice.controller.dto.ClientDto;
import ar.edu.unrn.userservice.domain.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RabbitService.
 */
@Service
@Slf4j
public class RabbitService {

  private final Producer producer;

  public RabbitService(Producer producer) {
    this.producer = producer;
  }

  public void sendClientUpdateMessage(ClientDto result) {
    log.info("Message '{}' will be send ...", result.toJsonString());
    this.producer.send(result.toJsonString());
  }
}