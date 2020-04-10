package com.enoxus.xbetspring.handlers;

import com.enoxus.xbetspring.dto.MessageDto;
import com.enoxus.xbetspring.service.MessageService;
import com.enoxus.xbetspring.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessageHandler extends TextWebSocketHandler {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        MessageDto messageDto = objectMapper.readValue(((String) message.getPayload()), MessageDto.class);

        sessionService.send(message, messageDto.getReceiver());
        sessionService.send(message, messageDto.getSender());

        messageService.save(messageDto);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionService.createSession(session);
    }
}
