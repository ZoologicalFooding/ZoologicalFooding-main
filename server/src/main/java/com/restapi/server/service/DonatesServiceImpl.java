package com.restapi.server.service;

import com.restapi.server.dao.DonatesDao;
import com.restapi.server.model.DonateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatesServiceImpl implements DonatesService {

    private final DonatesDao donatesDao;

    @Autowired
    public DonatesServiceImpl(DonatesDao donatesDao){ this.donatesDao = donatesDao; }



    @Override
    public void addDonates(DonateTable donate) {
        donatesDao.save(donate);
    }

    @Override
    public Iterable<DonateTable> getDonates() {
        return donatesDao.findAll();
    }

    @Override
    public DonateTable getDonatesById(int id) {
        return donatesDao.findById(id).get();
    }

    @Override
    public void deleteDonatesById(int id) {
        donatesDao.delete(donatesDao.findById(id).get());
    }


    @Override
    public void updateDonatesById(DonateTable donates, int id) {
        DonateTable oldDonate = donatesDao.findById(id).get();
        oldDonate.setCommet(donates.getCommet());
        oldDonate.setCreditCardNumber(donates.getCreditCardNumber());
        oldDonate.setCvvNumber(donates.getCvvNumber());
        oldDonate.setExpiration_date(donates.getExpiration_date());
        oldDonate.setFoodType(donates.getFoodType());
        oldDonate.setFullName(donates.getFullName());
        oldDonate.setContainerId(donates.getContainerId());
        oldDonate.setLiked(donates.getLiked());
    }

    @Override
    public void deleteAllDonates() {
        donatesDao.deleteAll();
    }
}
