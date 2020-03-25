package com.restapi.server.dao;

import com.restapi.server.model.Donate;
import org.springframework.data.repository.CrudRepository;

public interface DonateDao extends CrudRepository<Donate,Integer> {
}
