package com.restapi.server.controller;


import com.restapi.server.dao.MemberDao;
import com.restapi.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminTestController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PreAuthorize("hashAnyRole('ADMIN')")
    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody Member member){
        String pwd = member.getPass();
        String encPwd = passwordEncoder.encode(pwd);
        member.setPass(encPwd);
        memberDao.save(member);
        return "true";
    }


}
