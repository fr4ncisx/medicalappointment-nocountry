package com.healthcare.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * This creates a new queue of notification
     * @param userId the user that want to receive the message
     * @param msg actual message
     */
    public void sendNotification(String userId, String msg) {
        rabbitTemplate.convertAndSend("topicExchange", "notification.user." + userId, msg);
    }
}
