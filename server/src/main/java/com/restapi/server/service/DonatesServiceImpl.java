package com.restapi.server.service;

import com.restapi.server.dao.DonateDao;
import com.restapi.server.model.Donate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatesServiceImpl implements DonateService {
    private final DonateDao donateDao;

    @Autowired
    public DonatesServiceImpl(DonateDao donateDao){ this.donateDao = donateDao; }



    @Override
    public void addDonates(Donate donate) {
        donateDao.save(donate);
    }

    @Override
    public Iterable<Donate> getDonates() {
        return donateDao.findAll();
    }

    @Override
    public Donate getDonatesById(int id) {
        return donateDao.findById(id).get();
    }

    @Override
    public void deleteDonateById(int id) {
        donateDao.delete(donateDao.findById(id).get());
    }

    @Override
    public void updateDonateById(Donate donates, int id) {
        Donate oldDonate = donateDao.findById(id).get();
        oldDonate.setCommet(donates.getCommet());
        oldDonate.setCreditCardNumber(donates.getCreditCardNumber());
        oldDonate.setCvvNumber(donates.getCvvNumber());
        oldDonate.setExpiration_date(donates.getExpiration_date());
        oldDonate.setFoodType(donates.getFoodType());
        oldDonate.setFullName(donates.getFullName());
        oldDonate.setContainerId(donates.getContainerId());
    }

    @Override
    public void deleteAllDonates() {
        donateDao.deleteAll();
    }
}
