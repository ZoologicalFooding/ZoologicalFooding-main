package com.restapi.server.controller;

import com.restapi.server.model.*;
import com.restapi.server.service.*;
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
    private final DiscountService discountService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public DonateController(DiscountService discountService,ProCodeService proCodeService,DonatesService donatesService,FoodContainerService foodContainerService, EmailService emailService){
        this.donatesService = donatesService;
        this.foodContainerService = foodContainerService;
        this.proCodeService = proCodeService;
        this.emailService = emailService;
        this.discountService = discountService;
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
    public ResponseEntity<String> addDonate(@RequestBody DonateTable donate) {
        donate.setDonateType("CREDITCARD");
        donate.setPromotionCode("null");
        donate.setRecieverName("null");
        donate.setIban("null");
        int disCode = Integer.parseInt(donate.getDiscountCode());
        DiscountCodeTable discountCodeTable = discountService.getDisCodeCol(disCode);
        if(discountCodeTable!=null && discountCodeTable.getValidCol()==0){
            discountCodeTable.setValidCol(1);
            int amountColumn = Integer.parseInt(donate.getAmountStr());
            amountColumn = (amountColumn * 95) / 100;
            donate.setAmountStr(amountColumn+"");
            donatesService.addDonates(donate);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(donate.getDonaterMail());
            //message.setTo("zoologicalfooding@gmail.com");
            FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
            message.setSubject("You have donated for "+food.getName()+" "+ donate.getFullName()+" thanks!");
            message.setText("You have donated "+donate.getAmountStr()+" ,our little friends love you "+donate.getFullName()+ "! <3");
            javaMailSender.send(message);
            Email email = new Email();
            email.setMessageto(donate.getDonaterMail());
            email.setSenderFullName(donate.getFullName());
            emailService.addEmail(email);
            return ResponseEntity.ok(donate.getAmountStr());
        }else if(discountCodeTable==null) {
            donatesService.addDonates(donate);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(donate.getDonaterMail());
            FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
            message.setSubject("You have donated for "+food.getName()+" "+ donate.getFullName()+" thanks!");
            String textDisCode = "%5 discount code: "+discountService.getDisCodeMail()+"";
            message.setText("You have donated "+donate.getAmountStr()+" ,our little friends love you "+donate.getFullName()+ "! <3"+ " \n" +textDisCode);
            javaMailSender.send(message);
            Email email = new Email();
            email.setMessageto(donate.getDonaterMail());
            email.setSenderFullName(donate.getFullName());
            emailService.addEmail(email);
            return ResponseEntity.ok(donate.getAmountStr());
        }else if(discountCodeTable!=null && discountCodeTable.getValidCol()==1){
            donatesService.addDonates(donate);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(donate.getDonaterMail());
            FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
            message.setSubject("You have donated for "+food.getName()+" "+ donate.getFullName()+" thanks!");
            String textDisCode = "%5 discount code: "+discountService.getDisCodeMail()+"";
            message.setText("You have donated "+donate.getAmountStr()+" ,our little friends love you "+donate.getFullName()+ "! <3"+ " \n" +textDisCode);
            javaMailSender.send(message);
            Email email = new Email();
            email.setMessageto(donate.getDonaterMail());
            email.setSenderFullName(donate.getFullName());
            emailService.addEmail(email);
            return ResponseEntity.ok(donate.getAmountStr());
        } else
            return ResponseEntity.ok("Else");
/*
        donatesService.addDonates(donate);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(donate.getDonaterMail());
        //message.setTo("zoologicalfooding@gmail.com");
        FoodContainer food = foodContainerService.getContainerById(donate.getContainerId());
        message.setSubject(food.getName()+" adli kaba bagis yaptiniz "+ donate.getFullName()+" tesekkurler!");
        String textDisCode = "%5 indirim icin indirim kodunuz: "+discountService.getDisCodeMail()+"";
        message.setText(donate.getAmountStr()+" miktar bagis yaptiniz!, kucuk dostlarimiz size minnettar! <3" + " \n" +textDisCode);
        javaMailSender.send(message);
        Email email = new Email();
        email.setMessageto(donate.getDonaterMail());
        email.setSenderFullName(donate.getFullName());
        emailService.addEmail(email);
        */
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
        donate.setIban("");
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
        String textDisCode = "%5 indirim icin indirim kodunuz: "+discountService.getDisCodeMail()+"";
        message.setText(donate.getAmountStr()+" miktar bagis yaptiniz!, kucuk dostlarimiz size minnettar! <3" +" \n"+textDisCode);
        javaMailSender.send(message);
        Email email = new Email();
        email.setMessageto(donate.getDonaterMail());
        email.setSenderFullName(donate.getFullName());
        emailService.addEmail(email);
        return ResponseEntity.ok(donate);
    }

}
