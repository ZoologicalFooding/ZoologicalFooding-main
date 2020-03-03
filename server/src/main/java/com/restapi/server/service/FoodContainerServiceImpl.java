package com.restapi.server.service;

import com.restapi.server.dao.FoodContainerDao;
import com.restapi.server.model.FoodContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodContainerServiceImpl implements FoodContainerService {
    private final FoodContainerDao foodDao;

    @Autowired
    public FoodContainerServiceImpl(FoodContainerDao foodDao){
        this.foodDao=foodDao;
    }

    @Override
    public void addContainer(FoodContainer container) {
        foodDao.save(container);
    }

    @Override
    public Iterable<FoodContainer> getAllContainers() {
        return foodDao.findAll();
    }

    @Override
    public FoodContainer getContainerById(int id) {
        return foodDao.findById(id).get();
    }

    @Override
    public void deleteContainerById(int id) {
        foodDao.deleteById(id);
    }


    @Override
    public void updateContainerById(FoodContainer container, int id) {
        FoodContainer oldContainer = foodDao.findById(id).get();
        oldContainer.setAddress(container.getAddress());
        oldContainer.setLatitude(container.getLatitude());
        oldContainer.setLongitude(container.getLongitude());
        oldContainer.setName(container.getName());
        oldContainer.setType(container.getType());
        oldContainer.setWeight(container.getWeight());
        foodDao.save(oldContainer);
    }

    @Override
    public void deleteAllContainers() {
        foodDao.deleteAll();
    }
}
