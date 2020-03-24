package com.enoxus.xbetspring.service;

import com.mashape.unirest.http.HttpResponse;

public interface SMSService {
    String sendSignUpSms(String text, String number);
}
