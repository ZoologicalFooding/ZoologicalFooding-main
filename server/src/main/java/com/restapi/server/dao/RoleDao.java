package com.restapi.server.dao;

import com.restapi.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {

}
