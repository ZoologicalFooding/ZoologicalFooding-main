package com.restapi.server.controller;

import com.restapi.server.model.Email;
import com.restapi.server.service.EmailService;
import com.restapi.server.service.ProCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MailController {


    private final EmailService emailService;
    private final ProCodeService proCodeService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailController(EmailService emailService, ProCodeService proCodeService){
        this.emailService = emailService;
        this.proCodeService = proCodeService;
    }

    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Email>> getEmails() {
        Iterable<Email> emails = emailService.getAllEmail();
        return ResponseEntity.ok(emails);
    }
    @RequestMapping(value = "/email/{id}", method = RequestMethod.GET)
    public ResponseEntity<Email> getEmailById(@PathVariable int id) {
        return ResponseEntity.ok(emailService.getEmailById(id));
    }
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ResponseEntity<Email> sendEmails(@RequestBody Email email) {
        emailService.addEmail(email);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getMessageto());
        message.setSubject(email.getMessageSubject());
        String text = email.getRequestTypeMail()+"\n "+ email.getSenderFullName() +"\n"+ email.getSenderMail()
                +"\n"+email.getSenderPhone()+"\n"+email.getMailRequestAddress();
        message.setText(text);
        javaMailSender.send(message);

        return ResponseEntity.ok(email);
    }
    @RequestMapping(value = "/sendEmailProCode", method = RequestMethod.POST)
    public ResponseEntity<Email> sendProCodeMail(@RequestBody Email email) {
        emailService.addEmail(email);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getMessageto());
        message.setSubject(email.getMessageSubject());
        String text = proCodeService.getProCodeMail()+"";
        message.setText(text);
        javaMailSender.send(message);

        return ResponseEntity.ok(email);
    }
    @RequestMapping(value = "/deleteEmail/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Email> deleteEmail(@PathVariable int id) {
        Email email = emailService.getEmailById(id);
        emailService.deleteEmailById(id);
        return ResponseEntity.ok(email);
    }
    @RequestMapping(value = "/editEmail/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Email> editEmail(@RequestBody(required = false) Email email,@PathVariable int id) {
        emailService.updateEmailById(email,id);
        return ResponseEntity.ok(emailService.getEmailById(id));
    }

    /*
    @GetMapping("/sendEmail")
    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("zoologicalfooding@gmail.com");
        message.setSubject("spring app");
        message.setText("hi!");
        javaMailSender.send(message);
    }

    @PostMapping("/sendEmailDene")
    public void sendEmail2(@RequestBody Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getMessageto());
        message.setSubject(email.getMessageSubject());
        message.setText(email.getMessageBody());
        javaMailSender.send(message);
    }
*/
}
