package com.restapi.server.service;

import com.restapi.server.dao.DiscountCodeDao;
import com.restapi.server.model.DiscountCodeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiscountServiceImpl implements DiscountService {


    private final DiscountCodeDao discountCodeDao;

    @Autowired
    public DiscountServiceImpl(DiscountCodeDao discountCodeDao){
        this.discountCodeDao = discountCodeDao;
    }
    @Override
    public void addDisCode(DiscountCodeTable discount) {
        Random rand = new Random();
        int rand_int = rand.nextInt(1000000000);
        discount.setDisCodeCol(rand_int);
        discount.setValidCol(0);
        discountCodeDao.save(discount);
    }

    @Override
    public Iterable<DiscountCodeTable> getDisCode() {
        return discountCodeDao.findAll();
    }

    @Override
    public DiscountCodeTable getDisCodeById(int id) {
        return discountCodeDao.findById(id).get();
    }

    @Override
    public DiscountCodeTable getDisCodeCol(int disCodeCol) {
        for(DiscountCodeTable disCodeTable : getDisCode()){
            if(disCodeTable.getDisCodeCol() == disCodeCol) {
                return disCodeTable;
            }
        }
        return null;
    }

    @Override
    public int getDisCodeMail() {
        DiscountCodeTable code = new DiscountCodeTable();
        Random rand = new Random();
        int rand_int = rand.nextInt(1000000000);
        code.setDisCodeCol(rand_int);
        code.setValidCol(0);
        discountCodeDao.save(code);
        return code.getDisCodeCol();
    }
}
