package com.restapi.server.controller;

import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Fills;
import com.restapi.server.model.FoodContainer;
import com.restapi.server.service.CreditCardService;
import com.restapi.server.service.FillsService;
import com.restapi.server.service.FoodContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FillsController {

    private final FillsService fillsService;
    private final FoodContainerService foodService;
    private final CreditCardService creditService;

    @Autowired
    public FillsController(FillsService fillsService, FoodContainerService foodContainerService, CreditCardService creditService){
        this.fillsService=fillsService;
        this.foodService=foodContainerService;
        this.creditService = creditService;
    }

    @RequestMapping(value = "/fills",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Fills>> getFills() {
        Iterable<Fills> fills = fillsService.getFills();
        return ResponseEntity.ok(fills);
    }

    @RequestMapping(value = "/fill/{id}",method = RequestMethod.GET)
    public ResponseEntity<Fills> getFill(@PathVariable int id){
        return ResponseEntity.ok(fillsService.getFillsById(id));
    }

    @RequestMapping(value = "/deleteFill/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Fills> deleteFill(@PathVariable int id){
        Fills fill = fillsService.getFillsById(id);
        FoodContainer food = foodService.getContainerById(fill.getContainerId());
        List<Fills> newListFood = food.getFillsList();
        newListFood.remove(fill);
        CreditCard creditCard = creditService.getCreditCardByNumber(fill.getCreditCardNumber());
        List<Fills> newListCredit = creditCard.getFillsList();
        newListCredit.remove(fill);
        fillsService.deleteFillsById(id);
        return ResponseEntity.ok(fill);
    }

    @RequestMapping(value = "/deleteAllFills", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllFills(){
        fillsService.deleteAllFills();
        return ResponseEntity.ok("Delete All Fills!");
    }

    @RequestMapping(value = "/addFill", method = RequestMethod.POST)
    public ResponseEntity<Fills> addFill(@RequestBody Fills fills){
        fillsService.addFills(fills);
        return ResponseEntity.ok(fills);
    }

    @RequestMapping(value = "/editFills/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Fills> editFill(@RequestBody(required = false) Fills fills, @PathVariable int id){
        fillsService.updateFillsById(fills,id);
        return ResponseEntity.ok(fills);
    }







}
