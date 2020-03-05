package com.restapi.server.dao;

import com.restapi.server.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberDao extends JpaRepository<Member, Integer> {
       Member findByUsername(String username);
}
