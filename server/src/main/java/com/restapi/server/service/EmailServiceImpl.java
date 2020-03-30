package com.restapi.server.service;

import com.restapi.server.dao.EmailDao;
import com.restapi.server.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailDao emailDao;

    @Autowired
    public EmailServiceImpl(EmailDao emailDao){
        this.emailDao = emailDao;
    }

    @Override
    public void addEmail(Email email) {
        emailDao.save(email);
    }

    @Override
    public Iterable<Email> getAllEmail() {
        return emailDao.findAll();
    }

    @Override
    public Email getEmailById(int id) {
        return emailDao.findById(id).get();
    }

    @Override
    public void deleteEmailById(int id) {
        emailDao.delete(emailDao.findById(id).get());
    }

    @Override
    public void updateEmailById(Email email, int id) {
        Email oldEmail = emailDao.findById(id).get();
        oldEmail.setMessageBody(email.getMessageBody());
        oldEmail.setMessageto(email.getMessageto());
        oldEmail.setMessageSubject(email.getMessageSubject());
        oldEmail.setRequestTypeMail(email.getRequestTypeMail());
        oldEmail.setSenderFullName(email.getSenderFullName());
        oldEmail.setSenderMail(email.getSenderMail());
        oldEmail.setSenderPhone(email.getSenderPhone());
        oldEmail.setMailRequestAddress(email.getMailRequestAddress());
        emailDao.save(oldEmail);
    }
}
