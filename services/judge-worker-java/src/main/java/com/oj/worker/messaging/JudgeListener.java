package com.oj.worker.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JudgeListener {
  @RabbitListener(queues = "judge")
  public void handle(Object payload) {
    // TODO: implement docker-exec runner & grading
    System.out.println("Received judge task: " + payload);
  }
}
