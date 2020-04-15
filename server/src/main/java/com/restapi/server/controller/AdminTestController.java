package com.restapi.server.controller;


import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminTestController {
/*
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody Member member, Authentication authentication){
        String pwd = member.getPass();
        String encPwd = passwordEncoder.encode(pwd);
        member.setPass(encPwd);
        memberDao.save(member);
        return authentication.getName();
    }
*/

}
