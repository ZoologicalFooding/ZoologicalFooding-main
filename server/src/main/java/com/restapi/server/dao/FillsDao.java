package com.restapi.server.dao;

import com.restapi.server.model.Fills;
import org.springframework.data.repository.CrudRepository;

public interface FillsDao extends CrudRepository<Fills,Integer> {

}
