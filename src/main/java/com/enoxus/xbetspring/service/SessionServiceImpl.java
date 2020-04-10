package com.enoxus.xbetspring.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SessionServiceImpl implements SessionService {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void createSession(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    @SneakyThrows
    public void send(WebSocketMessage<?> message, String receiver) {
        for (WebSocketSession session : sessions) {
            if (session.getPrincipal().getName().equals(receiver) && session.isOpen()) {
                session.sendMessage(message);
            } else if (!session.isOpen()) {
                sessions.remove(session);
            }
        }
    }
}
