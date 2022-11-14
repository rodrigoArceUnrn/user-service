package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitService {

    @Autowired
    private Producer producer;

    public void sendToRabbit(String message){
        log.info("Message '{}' will be send ...", message);
        this.producer.send(message);
    }


}
