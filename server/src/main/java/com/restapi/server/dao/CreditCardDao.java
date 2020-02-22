package com.restapi.server.dao;

import com.restapi.server.model.CreditCard;
import org.springframework.data.repository.CrudRepository;

public interface CreditCardDao extends CrudRepository<CreditCard,Integer> {

}
