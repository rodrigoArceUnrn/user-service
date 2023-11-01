package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RabbitService.
 */
@Service
@Slf4j
public class RabbitService {

  @Autowired
  private Producer producer;

  public RabbitService() {
  }

  public void sendClientUpdateMessage(ClientDto result) {
    log.info("Message '{}' will be send ...", result.toJsonString());
    this.producer.send(result.toJsonString());
  }
}