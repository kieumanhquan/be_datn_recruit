package com.itsol.recruit.service.email;

import com.itsol.recruit.entity.OTP;
import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    void send(String to, String email);

    @Async
    void sendMailOrder(String to, String email);
}
