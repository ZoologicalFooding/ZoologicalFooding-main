package com.restapi.server.dao;

import com.restapi.server.model.DiscountCodeTable;
import org.springframework.data.repository.CrudRepository;

public interface DiscountCodeDao extends CrudRepository<DiscountCodeTable,Integer> {
}
