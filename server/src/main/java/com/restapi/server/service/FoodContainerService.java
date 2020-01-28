package com.restapi.server.service;

import com.restapi.server.model.FoodContainer;

public interface FoodContainerService {
    void addContainer(FoodContainer container);
    Iterable<FoodContainer> getAllContainers();
    FoodContainer getContainerById(int id);
    void deleteContainerById(int id);
    void updateContainerById(FoodContainer container,int id);
}
