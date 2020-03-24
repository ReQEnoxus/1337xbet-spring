package com.enoxus.xbetspring.service;

import com.enoxus.xbetspring.dto.SignUpDto;
import com.enoxus.xbetspring.entity.ConfirmationMessage;
import com.enoxus.xbetspring.entity.FileInfo;
import com.enoxus.xbetspring.entity.State;
import com.enoxus.xbetspring.entity.User;
import com.enoxus.xbetspring.exceptions.SignUpException;
import com.enoxus.xbetspring.repositories.FileInfoRepository;
import com.enoxus.xbetspring.repositories.UserRepository;
import com.enoxus.xbetspring.util.FileStorageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private SMSService smsService;

    @Override
    public void signUp(SignUpDto dto) {
        String raw = dto.getPassword();
        String encoded = passwordEncoder.encode(raw);

        userRepository.findByLogin(dto.getLogin()).ifPresent(user -> {
            throw new SignUpException("Логин уже занят");
        });

        FileInfo avatarFile = FileInfo.builder()
                .originalFileName("avi.png")
                .storageFileName("avi.png")
                .url(fileStorageUtil.getStoragePath() + "/" + "avi.png")
                .size(fileStorageUtil.sizeOf(fileStorageUtil.getStoragePath() + "/" + "avi.png"))
                .type("image/png")
                .build();

        fileInfoRepository.save(avatarFile);

        User user = User.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .balance(10_000d)
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .password(encoded)
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .avatar(avatarFile)
                .build();

        executorService.submit(() ->
                log.debug(smsService.sendSignUpSms("Вы успешно зарегистрировались. Подтвердите аккаунт, следуя инструкциям на почте", dto.getPhoneNumber()))
        );

        executorService.submit(() -> emailService.sendMail(ConfirmationMessage.builder()
                .code(user.getConfirmCode())
                .subject("Подтверждение регистрации")
                .targetMail(user.getEmail())
                .build()));

        userRepository.save(user);
    }
}