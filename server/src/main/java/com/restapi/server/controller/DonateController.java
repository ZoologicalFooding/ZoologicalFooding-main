package com.restapi.server.controller;

import com.restapi.server.model.DonateTable;
import com.restapi.server.model.Email;
import com.restapi.server.model.FoodContainer;
import com.restapi.server.model.ProCodeTable;
import com.restapi.server.service.DonatesService;
import com.restapi.server.service.EmailService;
import com.restapi.server.service.FoodContainerService;
import com.restapi.server.service.ProCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "*")
public class DonateController {

    private final DonatesService donatesService;
    private final FoodContainerService foodContainerService;
    private final ProCodeService proCodeService;
    private final EmailService emailService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public DonateController(ProCodeService proCodeService,DonatesService donatesService,FoodContainerService foodContainerService, EmailService emailService){
        this.donatesService = donatesService;
        this.foodContainerService = foodContainerService;
        this.proCodeService = proCodeService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/donates", method = RequestMethod.GET)
    public ResponseEntity<Iterable<DonateTable>> getDonates() {
        Iterable<DonateTable> donates = donatesService.getDonates();
        return ResponseEntity.ok(donates);
    }
    @RequestMapping(value = "/donate/{id}", method = RequestMethod.GET)
    public ResponseEntity<DonateTable> getDonate(@PathVariable int id) {
        return ResponseEntity.ok(donatesService.getDonatesById(id));
    }
    @RequestMapping(value = "/deleteDonate/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DonateTable> deleteDonate(@PathVariable int id) {
        DonateTable donate = donatesService.getDonatesById(id);
        FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
        List<DonateTable> listDonate = food.getDonatesList();
        listDonate.remove(donate);
        food.setDonatesList(listDonate);
        donatesService.deleteDonatesById(id);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/addDonate", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> addDonate(@RequestBody DonateTable donate) {
        donate.setDonateType("CREDITCARD");
        donate.setPromotionCode("null");
        donate.setRecieverName("null");
        donate.setIBAN("null");
        donatesService.addDonates(donate);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(donate.getDonaterMail());
        //message.setTo("zoologicalfooding@gmail.com");
        FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
        message.setSubject(food.getName()+" adli kaba bagis yaptiniz "+ donate.getFullName()+" tesekkurler!");
        message.setText(donate.getAmountStr()+" miktar bagis yaptiniz!, kucuk dostlarimiz size minnettar! <3");
        javaMailSender.send(message);
        Email email = new Email();
        email.setMessageto(donate.getDonaterMail());
        email.setSenderFullName(donate.getFullName());
        emailService.addEmail(email);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/editDonate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DonateTable> editDonate(@RequestBody(required = false) DonateTable donate, @PathVariable int id) {
        donatesService.updateDonatesById(donate,id);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/likeDonate/{id}", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> likeDonate(@PathVariable int id) {
        donatesService.likeDonate(id);
        return ResponseEntity.ok(donatesService.getDonatesById(id));
    }
    @RequestMapping(value = "/addDonateProCode", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> addDonateProCode(@RequestBody DonateTable donate) {
        donate.setDonateType("PROMOTIONCODE");
        donate.setRecieverName("");
        donate.setIBAN("");
        int proCode = Integer.parseInt(donate.getPromotionCode());
        ProCodeTable proCodeTable = proCodeService.getProCodeCol(proCode);
        if(proCodeTable != null && proCodeTable.getValid() == 0){
            proCodeTable.setValid(1);
            donatesService.addDonates(donate);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(donate.getDonaterMail());
            //message.setTo("zoologicalfooding@gmail.com");
            FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
            message.setSubject(food.getName()+" adli kaba bagis yaptiniz "+ donate.getFullName()+" tesekkurler!");
            message.setText(donate.getAmountStr()+" miktar bagis yaptiniz!, kucuk dostlarimiz size minnettar! <3");
            javaMailSender.send(message);
            Email email = new Email();
            email.setMessageto(donate.getDonaterMail());
            email.setSenderFullName(donate.getFullName());
            emailService.addEmail(email);
            return ResponseEntity.ok(donate);
        }else
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/addDonateEft", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> addDonateEft(@RequestBody DonateTable donate) {
        donate.setDonateType("EFT");
        donate.setPromotionCode("");
        donatesService.addDonates(donate);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(donate.getDonaterMail());
        //message.setTo("zoologicalfooding@gmail.com");
        FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
        message.setSubject(food.getName()+" adli kaba bagis yaptiniz "+ donate.getFullName()+" tesekkurler!");
        message.setText(donate.getAmountStr()+" miktar bagis yaptiniz!, kucuk dostlarimiz size minnettar! <3");
        javaMailSender.send(message);
        Email email = new Email();
        email.setMessageto(donate.getDonaterMail());
        email.setSenderFullName(donate.getFullName());
        emailService.addEmail(email);
        return ResponseEntity.ok(donate);
    }

}
