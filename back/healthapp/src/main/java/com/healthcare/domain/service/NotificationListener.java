package com.healthcare.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationListener {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * This receives every rabbitmq message
     * and send it to a private websocket channel
     * @param userId the user that want to receive the message
     * @param msg actual message
     */
    @RabbitListener(queues = "notification")
    public void receiveMessage(String userId, String msg) {
        messagingTemplate.convertAndSend("/user/" + userId + "/notifications", msg);
    }

}
