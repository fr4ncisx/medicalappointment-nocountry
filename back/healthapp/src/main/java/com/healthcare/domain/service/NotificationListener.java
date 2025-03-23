package com.healthcare.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
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
     * we request by the securitycontext the userId and then do the validations
     *the user that want to receive the message
     * @param message Message from the service
     */
    @RabbitListener(queues = "notification")
    public void receiveMessage(Message message) {
        String payload = new String(message.getBody());
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        String userId = routingKey.split("\\.")[2];
        messagingTemplate.convertAndSendToUser(userId, "/notifications", payload);
    }

}
