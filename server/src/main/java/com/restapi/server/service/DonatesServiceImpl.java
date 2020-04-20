package com.restapi.server.service;

import com.restapi.server.dao.DonatesDao;
import com.restapi.server.model.DonateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DonatesServiceImpl implements DonatesService {

    private final DonatesDao donatesDao;

    @Autowired
    public DonatesServiceImpl(DonatesDao donatesDao){ this.donatesDao = donatesDao; }

    @Override
    public void addDonates(DonateTable donate) {
        donate.setDonateTime(new Date());
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
        oldDonate.setAmountStr(donates.getAmountStr());
        oldDonate.setCreditCardNumberStr(donates.getCreditCardNumberStr());
        oldDonate.setCvvNumberStr(donates.getCvvNumberStr());
        oldDonate.setExpiration_dateStr(donates.getExpiration_dateStr());
        oldDonate.setFoodType(donates.getFoodType());
        oldDonate.setFullName(donates.getFullName());
        oldDonate.setContainerId(donates.getContainerId());
        oldDonate.setLikedStr(donates.getLikedStr());
        oldDonate.setPromotionCode(donates.getPromotionCode());
        oldDonate.setDonateType(donates.getDonateType());
        oldDonate.setIban(donates.getIban());
        oldDonate.setRecieverName(donates.getIban());
        oldDonate.setDonateFoodName(donates.getDonateFoodName());
        donatesDao.save(oldDonate);
    }

    @Override
    public void deleteAllDonates() {
        donatesDao.deleteAll();
    }

    @Override
    public void likeDonate(int id) {
        DonateTable donateTable = donatesDao.findById(id).get();
        if(donateTable.getLikedStr()==null)
            donateTable.setLikedStr("0");
        int like = Integer.parseInt(donateTable.getLikedStr());
        like = like + 1;
        donateTable.setLikedStr(like+"");
        donatesDao.save(donateTable);
    }
}
