package com.restapi.server.controller;

import com.restapi.server.model.Donate;
import com.restapi.server.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class DonateController {

    private final DonateService donateService;

    @Autowired
    public DonateController(DonateService donateService){
        this.donateService = donateService;
    }

    @RequestMapping(value = "/donates", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Donate>> getDonates() {
        Iterable<Donate> donates = donateService.getDonates();
        return ResponseEntity.ok(donates);
    }
    @RequestMapping(value = "/donate/{id}", method = RequestMethod.GET)
    public ResponseEntity<Donate> getDonate(@PathVariable int id) {
        return ResponseEntity.ok(donateService.getDonatesById(id));
    }

    @RequestMapping(value = "/deleteDonate/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Donate> deleteDonate(@PathVariable int id) {
        Donate donate = donateService.getDonatesById(id);
        donateService.deleteDonateById(id);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/addDonate", method = RequestMethod.POST)
    public ResponseEntity<Donate> addFill(@RequestBody Donate donate, Authentication authentication) {
        //Member memb = memberService.findByUserName(authentication.getName());
        //donateService.setMemberId(memb.getMemberID());
        donateService.addDonates(donate);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/editDonate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Donate> editDonate(@RequestBody(required = false) Donate donate, @PathVariable int id) {
        donateService.updateDonateById(donate,id);
        return ResponseEntity.ok(donate);
    }
}
