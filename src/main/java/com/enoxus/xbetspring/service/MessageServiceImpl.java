package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.MessageDto;
import com.enoxus.xbetspring.entity.Message;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.repositories.MessageRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(MessageDto dto) {

        Optional<User> userFirst = userRepository.findByLogin(dto.getSender());
        Optional<User> userSecond = userRepository.findByLogin(dto.getReceiver());

        if (userFirst.isPresent() && userSecond.isPresent()) {
            messageRepository.save(Message.builder()
                    .receiver(userSecond.get())
                    .sender(userFirst.get())
                    .text(dto.getText())
                    .build());
        }
    }

    @Override
    public List<MessageDto> getDialog(String firstLogin, String secondLogin) {
        List<Message> dialogRaw = messageRepository.getAllBySenderLoginAndReceiverLogin(firstLogin, secondLogin);
        dialogRaw.addAll(messageRepository.getAllBySenderLoginAndReceiverLogin(secondLogin, firstLogin));
        dialogRaw.sort(Comparator.comparingLong(Message::getId));
        return dialogRaw.stream().map(msg -> MessageDto.builder()
                .text(msg.getText())
                .sender(msg.getSender().getLogin())
                .receiver(msg.getReceiver().getLogin())
                .build())
                .collect(Collectors.toList());
    }
}
