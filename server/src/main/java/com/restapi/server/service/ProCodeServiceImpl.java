package com.restapi.server.service;

import com.restapi.server.dao.ProCodeTableDao;
import com.restapi.server.model.ProCodeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProCodeServiceImpl implements ProCodeService {
    private final ProCodeTableDao proCodeTableDao;

    @Autowired
    public ProCodeServiceImpl(ProCodeTableDao proCodeTableDao){
        this.proCodeTableDao = proCodeTableDao;
    }

    @Override
    public void addProCode(ProCodeTable code) {
        Random rand = new Random();
        int rand_int = rand.nextInt(1000000000);
        code.setProCodeCol(rand_int);
        code.setValid(0);
        proCodeTableDao.save(code);
    }

    @Override
    public Iterable<ProCodeTable> getProCodes() {
        return proCodeTableDao.findAll();
    }

    @Override
    public ProCodeTable getProCodeById(int id) {
        return proCodeTableDao.findById(id).get();
    }

    @Override
    public ProCodeTable getProCodeCol(int proCodeCol) {
        for(ProCodeTable proCodeTable : getProCodes()){
            if(proCodeTable.getProCodeCol()==proCodeCol) {
                return proCodeTable;
            }
        }
        return null;
    }

    @Override
    public int getProCodeMail(){
        ProCodeTable code = new ProCodeTable();
        Random rand = new Random();
        int rand_int = rand.nextInt(1000000000);
        code.setProCodeCol(rand_int);
        code.setValid(0);
        proCodeTableDao.save(code);
        return code.getProCodeCol();
    }
}
