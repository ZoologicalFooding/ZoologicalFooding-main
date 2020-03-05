package com.restapi.server.service;

import com.restapi.server.dao.MemberDao;
import com.restapi.server.model.CustomUserDetails;
import com.restapi.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberDao.findByUsername(username);
        CustomUserDetails userDetails = null;
        if (member != null) {
            userDetails = new CustomUserDetails();
            userDetails.setMember(member);

        } else {
            throw new UsernameNotFoundException("User not exist with name: " + username);
        }
        return userDetails;
    }
}
