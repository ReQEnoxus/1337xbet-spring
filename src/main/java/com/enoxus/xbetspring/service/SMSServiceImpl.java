package com.enoxus.xbetspring.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SMSServiceImpl implements SMSService {

    private String prepareUrl(String text, String number) {
        String url = "https://"
                + "gate.smsaero.ru/v2/sms/send?number="
                + number
                + "&text="
                + text
                + "&sign="
                + "SMS Aero"
                + "&channel=DIRECT";
        url = url.replaceAll(" ", "%20");
        return url;
    }

    @SneakyThrows
    @Override
    public String sendSignUpSms(String text, String number) {
        return Unirest.get(prepareUrl(text, number)).basicAuth("reqenoxus@gmail.com", "ngfhIBLoFISwQGFnhbTC4CcwMDqM").asJson().toString();
    }
}
