package com.restapi.server.controller;

import com.restapi.server.model.DonateTable;
import com.restapi.server.service.DonatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class DonateController {

    private final DonatesService donatesService;

    @Autowired
    public DonateController(DonatesService donatesService){
        this.donatesService = donatesService;
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
        donatesService.deleteDonatesById(id);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/addDonate", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> addFill(@RequestBody DonateTable donate, Authentication authentication) {
        //Member memb = memberService.findByUserName(authentication.getName());
        //donateService.setMemberId(memb.getMemberID());
        donatesService.addDonates(donate);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/editDonate/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DonateTable> editDonate(@RequestBody(required = false) DonateTable donate, @PathVariable int id) {
        donatesService.updateDonatesById(donate,id);
        return ResponseEntity.ok(donate);
    }


}
