package com.restapi.server.service;

import com.restapi.server.dao.CreditCardDao;
import com.restapi.server.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardDao creditCardDao;


    @Autowired
    public CreditCardServiceImpl(CreditCardDao creditCardDao){ this.creditCardDao = creditCardDao; }

    @Override
    public void addCreditCard(CreditCard creditCard) { creditCardDao.save(creditCard); }

    @Override
    public Iterable<CreditCard> getCreditCards() { return creditCardDao.findAll(); }

    @Override
    public CreditCard getCreditCardByNumber(int number) { return creditCardDao.findById(number).get(); }

    @Override
    public void deleteCreditCardByNumber(int number) { creditCardDao.deleteById(number); }

    @Override
    public void updateCreditCardByNumber(CreditCard card, int number) {
        CreditCard oldCreditCard = getCreditCardByNumber(number);
        oldCreditCard.setFullName(card.getFullName());
        oldCreditCard.setExpiration_date(card.getExpiration_date());
        oldCreditCard.setCvvNumber(card.getCvvNumber());
        creditCardDao.save(oldCreditCard);
    }

    @Override
    public void deleteAllCreditCards() { creditCardDao.deleteAll(); }

}