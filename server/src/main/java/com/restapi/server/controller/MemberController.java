package com.restapi.server.controller;

import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Member;
import com.restapi.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Member>> getMembers() {
        Iterable<Member> memberList = memberService.getAllMembers();
        return ResponseEntity.ok(memberList);
    }

    @RequestMapping(value = "/member/{id}",method = RequestMethod.GET)
    public ResponseEntity<Member> getMember(@PathVariable int id) {
        Member memb = memberService.getMemberById(id);
        return ResponseEntity.ok(memb);
    }

    @RequestMapping(value = "/addMember",method = RequestMethod.POST)
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return ResponseEntity.ok(member);
    }
    @RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Member> deleteMember(@PathVariable int id) {
        Member mem = memberService.getMemberById(id);
        memberService.deleteMemberById(id);
        return ResponseEntity.ok(mem);
    }

    @RequestMapping(value = "/editMember/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Member> updateMember(@RequestBody(required = false) Member mem, @PathVariable int id) {
        memberService.updateMemberById(mem,id);
        return ResponseEntity.ok(mem);
    }
    @RequestMapping(value = "/deleteAllMembers", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllMembers(){
        memberService.deleteAllMembers();
        return ResponseEntity.ok("Delete All Members!");
    }

    // this method is set to be deleted later, please ignore this method!!!!!!!!!!!!!!!!!
    @RequestMapping(value = "/denemeKrediKarti", method = RequestMethod.POST)
    public ResponseEntity<String> krediKarti(@RequestBody Member member){
        CreditCard cr1 = new CreditCard();
        CreditCard cr2 = new CreditCard();
        cr1.setCardNumber(1212131);
        cr1.setCvvNumber(2323232);
        cr1.setExpiration_date(232323);
        cr1.setFullName("denemeilk");

        cr2.setCvvNumber(2222222);
        cr2.setFullName("denemeIKi");
        cr2.setExpiration_date(2324242);
        cr2.setCardNumber(13445677);

        List<CreditCard> tempList = new ArrayList<>();
        tempList.add(cr1);
        tempList.add(cr2);
        member.setCreditCardList(tempList);
        memberService.addMember(member);

        return ResponseEntity.ok("Delete All Members!");
    }
}
