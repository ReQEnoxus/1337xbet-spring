package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.entity.ConfirmationMessage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Value("${spring.mail.username}")
    private String userName;


    @Override
    public void sendMail(ConfirmationMessage message) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = freemarkerConfig.getTemplate("email.ftlh");

            Map<String, Object> model = new HashMap<>();
            model.put("code", message.getCode());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(message.getTargetMail());
            helper.setText(html, true);
            helper.setFrom(userName);
            helper.setSubject(message.getSubject());

            javaMailSender.send(msg);

        } catch (MessagingException | IOException | TemplateException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}