package com.company.jmix.task1.controller;

import com.company.jmix.task1.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/list.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        logger.info("controller username: "+ headerAccessor.getUser().getName());
        headerAccessor.getSessionAttributes().put("username", headerAccessor.getUser().getName());
        chatMessage.setSender(headerAccessor.getUser().getName());

        return chatMessage;
    }
}
