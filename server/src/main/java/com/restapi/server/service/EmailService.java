package com.restapi.server.service;

import com.restapi.server.model.Email;

public interface EmailService {
    void addEmail(Email email);
    Iterable<Email> getAllEmail();
    Email getEmailById(int id);
    void deleteEmailById(int id);
    void updateEmailById(Email email,int id);
}
