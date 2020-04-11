package com.restapi.server.service;

import com.restapi.server.dao.FoodContainerDao;
import com.restapi.server.model.FoodContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FoodContainerServiceImpl implements FoodContainerService {
    private final FoodContainerDao foodDao;

    @Autowired
    public FoodContainerServiceImpl(FoodContainerDao foodDao){
        this.foodDao=foodDao;
    }

    @Override
    public void addContainer(FoodContainer container) {
        container.setContainerAddTime(new Date());
        container.setContainerUpdateTime(new Date());
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
        oldContainer.setCity(container.getCity());
        oldContainer.setCountry(container.getCountry());
        oldContainer.setIP(container.getIP());
        oldContainer.setRegion(container.getRegion());
        oldContainer.setContainerUpdateTime(new Date());
        oldContainer.setStatus(container.getStatus());
        oldContainer.setPassCont(container.getPassCont());
        foodDao.save(oldContainer);
    }

    @Override
    public void deleteAllContainers() {
        foodDao.deleteAll();
    }
}
