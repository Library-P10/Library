package com.api.library.service.contract;

public interface MailService {

    void sendMessage(String argTo, String argFirst, String argLast, String argTitle);
}
