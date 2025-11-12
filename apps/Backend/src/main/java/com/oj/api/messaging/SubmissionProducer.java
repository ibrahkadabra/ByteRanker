package com.oj.api.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.oj.api.config.RabbitConfig;

@Component
@RequiredArgsConstructor
public class SubmissionProducer {
  private final RabbitTemplate rabbit;
  public void enqueueJudge(Object payload) { // replace Object with your DTO
    rabbit.convertAndSend(RabbitConfig.JUDGE_QUEUE, payload);
  }
}
