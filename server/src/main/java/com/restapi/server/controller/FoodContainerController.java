package com.restapi.server.controller;

import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Fills;
import com.restapi.server.model.FoodContainer;
import com.restapi.server.service.FoodContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FoodContainerController {
    private final FoodContainerService foodContainerService;

    @Autowired
    public FoodContainerController(FoodContainerService foodContainerService){
        this.foodContainerService=foodContainerService;
    }

    @RequestMapping(value = "/containers",method = RequestMethod.GET)
    public ResponseEntity<Iterable<FoodContainer>> getContainers() {
        Iterable<FoodContainer> contList = foodContainerService.getAllContainers();
        return ResponseEntity.ok(contList);
    }

    @RequestMapping(value = "/container/{id}",method = RequestMethod.GET)
    public ResponseEntity<FoodContainer> getContainer(@PathVariable int id) {
        FoodContainer cont = foodContainerService.getContainerById(id);
        return ResponseEntity.ok(cont);
    }
    @RequestMapping(value = "/addContainer",method = RequestMethod.POST)
    public ResponseEntity<FoodContainer> addContainer(@RequestBody FoodContainer cont) {
        foodContainerService.addContainer(cont);
        return ResponseEntity.ok(cont);
    }
    @RequestMapping(value = "/deleteContainer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FoodContainer> deleteContainer(@PathVariable int id) {
        FoodContainer foodContainer = foodContainerService.getContainerById(id);
        foodContainerService.deleteContainerById(id);
        return ResponseEntity.ok(foodContainer);
    }
    @RequestMapping(value = "/deleteAllContainers", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllContainers(){
        foodContainerService.deleteAllContainers();
        return ResponseEntity.ok("Delete All Member!");
    }

    @RequestMapping(value = "/editContainer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FoodContainer> updateContainers(@RequestBody(required = false) FoodContainer cont, @PathVariable int id) {
        foodContainerService.updateContainerById(cont,id);
        return ResponseEntity.ok(cont);
    }
    // this method will be deleted later.
    @RequestMapping(value = "/denemeOdeme", method = RequestMethod.POST)
    public ResponseEntity<String> odeme(@RequestBody FoodContainer food){
        CreditCard cr1 = new CreditCard();
        CreditCard cr2 = new CreditCard();

        cr1.setCardNumber(1212131);
        cr1.setCvvNumber(2323232);
        cr1.setExpiration_date(232323);
        cr1.setFullName("denemeilk");


        Fills fill = new Fills();
        fill.setCommet("20");
        fill.setFoodType("cat");
        fill.setCreditCardNumber(cr1.getCardNumber());
        List<Fills> tempList = new ArrayList<>();
        tempList.add(fill);
        cr1.setFillsList(tempList);
        food.setFillsList(tempList);
        foodContainerService.addContainer(food);

        return ResponseEntity.ok("Delete All Members!");
    }


}
