package com.restapi.server.dao;

import com.restapi.server.model.FoodContainer;
import org.springframework.data.repository.CrudRepository;


public interface FoodContainerDao extends CrudRepository<FoodContainer,Integer> {
}
