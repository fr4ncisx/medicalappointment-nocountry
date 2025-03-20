package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.RabbitRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * This creates a new queue of notification
     * @param rabbitRequest dto to send notification
     */
    public void sendNotification(RabbitRequest rabbitRequest) {
        rabbitTemplate.convertAndSend("topicExchange", "notification.user." + rabbitRequest.getId(), rabbitRequest.getPayload());
    }
}
