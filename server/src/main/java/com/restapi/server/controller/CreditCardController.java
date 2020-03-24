package com.restapi.server.controller;

import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Member;
import com.restapi.server.service.CreditCardService;
import com.restapi.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final MemberService  memberService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService, MemberService memberService) {
        this.creditCardService = creditCardService;
        this.memberService = memberService;
    }
    // Add method id dönsün.

    @RequestMapping(value = "/creditCards",method = RequestMethod.GET)
    public ResponseEntity<Iterable<CreditCard>> getCreditCards() {
        Iterable<CreditCard> creditCardList = creditCardService.getCreditCards();
        return ResponseEntity.ok(creditCardList);
    }

    @RequestMapping(value = "/authCreditCards",method = RequestMethod.GET)
    public ResponseEntity<Iterable<CreditCard>> getCreditCardAuth(Authentication authentication) {
        Member memb = memberService.findByUserName(authentication.getName());
        Iterable<CreditCard> creditCardList = memb.getCreditCardList();
        return ResponseEntity.ok(creditCardList);
    }

    @RequestMapping(value = "/creditCard/{number}",method = RequestMethod.GET)
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable int number) {
        CreditCard creditCard = creditCardService.getCreditCardByNumber(number);
        return ResponseEntity.ok(creditCard);
    }

    @RequestMapping(value = "/addCreditCard",method = RequestMethod.POST)
    public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCard creditCard, Authentication authentication) {
        Member memb = memberService.findByUserName(authentication.getName());
        creditCard.setMemberId(memb.getMemberID());
        creditCardService.addCreditCard(creditCard);
        return ResponseEntity.ok(creditCard);
    }
    @RequestMapping(value = "/deleteCreditCard/{number}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCreditCard(@PathVariable int number) {
        CreditCard credit = creditCardService.getCreditCardByNumber(number);
        int memberId = credit.getMemberId();
        Member member = memberService.getMemberById(memberId);
        List<CreditCard> list = member.getCreditCardList();
        list.remove(credit);
        member.setCreditCardList(list);
        creditCardService.deleteCreditCardByNumber(number);
        return ResponseEntity.ok("Deleted!");
    }

    @RequestMapping(value = "/editCreditCard/{number}", method = RequestMethod.PUT)
    public ResponseEntity<CreditCard> updateCreditCard(@RequestBody(required = false) CreditCard creditCard, @PathVariable int number) {
        creditCardService.updateCreditCardByNumber(creditCard,number);
        return ResponseEntity.ok(creditCard);
    }

    @RequestMapping(value = "/deleteAllCreditCars", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllCards(){
        creditCardService.deleteAllCreditCards();
        return ResponseEntity.ok("Deleted All Cards!");
    }

}
