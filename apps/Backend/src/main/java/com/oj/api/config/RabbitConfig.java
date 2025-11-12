package com.oj.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String JUDGE_QUEUE = "judge";
  public static final String RANKS_QUEUE = "ranks";

  @Bean Queue judgeQueue() { return QueueBuilder.durable(JUDGE_QUEUE).build(); }
  @Bean Queue ranksQueue() { return QueueBuilder.durable(RANKS_QUEUE).build(); }

  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory cf, ObjectMapper om) {
    RabbitTemplate rt = new RabbitTemplate(cf);
    rt.setMessageConverter(new Jackson2JsonMessageConverter(om));
    return rt;
  }
}
