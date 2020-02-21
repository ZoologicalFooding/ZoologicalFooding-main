package com.restapi.server.controller;

import com.restapi.server.model.Member;
import com.restapi.server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
