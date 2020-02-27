package com.restapi.server.service;

import com.restapi.server.dao.FillsDao;
import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Fills;
import com.restapi.server.model.FoodContainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FillsServiceImpl implements FillsService {
    private final FillsDao fillsDao;

    private final FoodContainerService foodContainerService;

    private final CreditCardService creditCardService;

    @Autowired
    public FillsServiceImpl(FillsDao fillsDao, FoodContainerService foodContainerService, CreditCardService creditCardService){
        this.fillsDao = fillsDao;
        this.foodContainerService = foodContainerService;
        this.creditCardService = creditCardService;
    }


    @Override
    public void addFills(Fills fills, int containerID, int cardNumber) {

        FoodContainer foodContainer = foodContainerService.getContainerById(containerID);
        CreditCard creditCard = creditCardService.getCreditCardByNumber(cardNumber);

        List<Fills> foodList = foodContainer.getFillsList();
        List<Fills> creditList = creditCard.getFillsList();

        fills.setMemberId(creditCard.getMemberId());
        fills.setContainerId(foodContainer.getContainerID());

        foodList.add(fills);
        creditList.add(fills);
        fillsDao.save(fills);


    }

    @Override
    public Iterable<Fills> getFills() {
        return fillsDao.findAll();
    }

    @Override
    public Fills getFillsById(int id) {
        return fillsDao.findById(id).get();
    }

    @Override
    public void deleteFillsById(int id) {
        fillsDao.deleteById(id);
    }

    @Override
    public void updateFillsById(Fills fills, int id) {
        Fills oldFills = fillsDao.findById(id).get();
        oldFills.setFoodType(fills.getFoodType());
        oldFills.setContainerId(fills.getContainerId());
        oldFills.setCreditCardNumber(fills.getCreditCardNumber());
        oldFills.setCommet(fills.getCommet());
        oldFills.setMemberId(fills.getMemberId());
        fillsDao.save(oldFills);
    }

    @Override
    public void deleteAllFills() {
        fillsDao.deleteAll();
    }
}
