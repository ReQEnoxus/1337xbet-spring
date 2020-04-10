package com.enoxus.xbetspring.repositories;

import com.enoxus.xbetspring.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllBySenderLogin(String login);

    List<Message> getAllByReceiverLogin(String login);

    List<Message> getAllBySenderLoginAndReceiverLogin(String senderLogin, String receiverLogin);
}
