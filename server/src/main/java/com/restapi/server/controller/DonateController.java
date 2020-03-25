package com.restapi.server.controller;

import com.restapi.server.model.DonateTable;
import com.restapi.server.model.FoodContainer;
import com.restapi.server.service.DonatesService;
import com.restapi.server.service.FoodContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class DonateController {

    private final DonatesService donatesService;
    private final FoodContainerService foodContainerService;

    @Autowired
    public DonateController(DonatesService donatesService,FoodContainerService foodContainerService){
        this.donatesService = donatesService;
        this.foodContainerService = foodContainerService;
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
        food.getDonatesList().remove(donate);
        donatesService.deleteDonatesById(id);
        return ResponseEntity.ok(donate);
    }
    @RequestMapping(value = "/addDonate", method = RequestMethod.POST)
    public ResponseEntity<DonateTable> addDonate(@RequestBody DonateTable donate, Authentication authentication) {
        donatesService.addDonates(donate);
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

}
