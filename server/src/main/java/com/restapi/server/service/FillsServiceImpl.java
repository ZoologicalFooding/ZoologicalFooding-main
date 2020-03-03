package com.restapi.server.service;

import com.restapi.server.dao.FillsDao;
import com.restapi.server.model.Fills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillsServiceImpl implements FillsService {
    private final FillsDao fillsDao;

    @Autowired
    public FillsServiceImpl(FillsDao fillsDao){ this.fillsDao = fillsDao; }

    @Override
    public void addFills(Fills fills) { fillsDao.save(fills); }

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
    public void deleteAllFills() { fillsDao.deleteAll(); }
}
