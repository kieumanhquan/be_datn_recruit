package com.itsol.recruit.service.email;

import com.itsol.recruit.entity.OTP;

public interface EmailSender {
    void send(String to, String email);
}
