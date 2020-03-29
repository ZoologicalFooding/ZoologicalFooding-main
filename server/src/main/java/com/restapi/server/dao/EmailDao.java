package com.restapi.server.dao;

import com.restapi.server.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailDao extends CrudRepository<Email,Integer> {
}
