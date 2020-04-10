package com.enoxus.xbetspring.service;

import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public interface SessionService {

    void createSession(WebSocketSession session);

    void send(WebSocketMessage<?> message, String receiver);
}
