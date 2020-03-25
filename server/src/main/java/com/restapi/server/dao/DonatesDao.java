package com.restapi.server.dao;

import com.restapi.server.model.DonateTable;
import org.springframework.data.repository.CrudRepository;

public interface DonatesDao extends CrudRepository<DonateTable,Integer> {
}
