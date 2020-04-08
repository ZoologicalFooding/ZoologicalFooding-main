package com.restapi.server.controller;

import com.restapi.server.model.DiscountCodeTable;
import com.restapi.server.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping(value = "/disCodes",method = RequestMethod.GET)
    public ResponseEntity<Iterable<DiscountCodeTable>> getCodes() {
        Iterable<DiscountCodeTable> proCodesList = discountService.getDisCode();
        return ResponseEntity.ok(proCodesList);
    }

    @RequestMapping(value = "/disCode/{number}",method = RequestMethod.GET)
    public ResponseEntity<DiscountCodeTable> getCodesByCode(@PathVariable int number) {
        return ResponseEntity.ok(discountService.getDisCodeCol(number));
    }

    @RequestMapping(value = "/generateDisCode",method = RequestMethod.GET)
    public ResponseEntity<Integer> addDisCode(){
        DiscountCodeTable discountCodeTable = new DiscountCodeTable();
        discountService.addDisCode(discountCodeTable);
        return ResponseEntity.ok(discountCodeTable.getDisCodeCol());
    }
}
