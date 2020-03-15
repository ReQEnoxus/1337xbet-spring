package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.entity.ConfirmationMessage;

public interface EmailService {
    void sendMail(ConfirmationMessage message);
}