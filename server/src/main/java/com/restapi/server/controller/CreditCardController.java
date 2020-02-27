package com.restapi.server.controller;

import com.restapi.server.model.CreditCard;
import com.restapi.server.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @RequestMapping(value = "/creditCards",method = RequestMethod.GET)
    public ResponseEntity<Iterable<CreditCard>> getCreditCards() {
        Iterable<CreditCard> creditCardList = creditCardService.getCreditCards();
        return ResponseEntity.ok(creditCardList);
    }

    @RequestMapping(value = "/creditCard/{number}",method = RequestMethod.GET)
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable int number) {
        CreditCard creditCard = creditCardService.getCreditCardByNumber(number);
        return ResponseEntity.ok(creditCard);
    }

    @RequestMapping(value = "/addCreditCard/{id}",method = RequestMethod.POST)
    public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCard creditCard, @PathVariable int id) {
        creditCardService.addCreditCard(creditCard,id);
        return ResponseEntity.ok(creditCard);
    }
    @RequestMapping(value = "/deleteCreditCard/{number}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCreditCard(@PathVariable int number) {
       creditCardService.deleteCreditCardByNumber(number);
        return ResponseEntity.ok("Deleted!");
    }





}
