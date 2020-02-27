package com.restapi.server.service;

import com.restapi.server.model.CreditCard;

public interface CreditCardService {
    void addCreditCard(CreditCard creditCard);
    Iterable<CreditCard> getCreditCards();
    CreditCard getCreditCardByNumber(int number);
    void deleteCreditCardByNumber(int number);
    void updateCreditCardByNumber(CreditCard card,int number);
    void deleteAllCreditCards();

}
