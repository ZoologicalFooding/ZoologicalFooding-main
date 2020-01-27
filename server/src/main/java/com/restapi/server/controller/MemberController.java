package com.restapi.server.controller;

import com.restapi.server.model.Member;
import com.restapi.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Member>> getMembersRest() {
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
    
}
