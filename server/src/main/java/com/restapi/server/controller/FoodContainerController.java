package com.restapi.server.controller;

import com.restapi.server.model.FoodContainer;
import com.restapi.server.service.FoodContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @RequestMapping(value = "/containers/{id}",method = RequestMethod.GET)
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

    @RequestMapping(value = "/editContainer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FoodContainer> updateContainers(@RequestBody(required = false) FoodContainer cont, @PathVariable int id) {
        foodContainerService.updateContainerById(cont,id);
        return ResponseEntity.ok(cont);
    }


}
