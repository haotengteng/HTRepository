package com.tool;

import com.model.LogDo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/1/4.
 */
public class MQLogSender {

    private RabbitTemplate rabbitTemplate;

    public void sender(LogDo logDo) {
        rabbitTemplate.convertAndSend(logDo);
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
