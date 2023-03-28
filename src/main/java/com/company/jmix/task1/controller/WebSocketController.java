package com.company.jmix.task1.controller;

import com.company.jmix.task1.entity.StatusMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {



    @MessageMapping("/list.displayUser")
    @SendTo("/topic/public")
    public StatusMessage displayUser(@Payload StatusMessage statusMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", headerAccessor.getUser().getName());
        statusMessage.setSender(headerAccessor.getUser().getName());

        return statusMessage;
    }
}
