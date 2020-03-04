package com.restapi.server.dao;

import com.restapi.server.model.Member;
import org.springframework.data.repository.CrudRepository;


public interface MemberDao extends CrudRepository<Member, Integer> {
       //Member findByName(String username);
}
