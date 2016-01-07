package com.tool;

import com.model.LogDo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by Administrator on 2016/1/4.
 */
public class MQLogSender {

    private RabbitTemplate rabbitTemplate;

    public void sender(LogDo logDo) {
//        JSONObject json = new JSONObject(logDo);
//        rabbitTemplate.convertAndSend(json.toString());
        rabbitTemplate.convertAndSend(logDo);
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
