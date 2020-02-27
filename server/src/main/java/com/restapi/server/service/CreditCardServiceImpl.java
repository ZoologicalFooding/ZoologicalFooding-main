package com.restapi.server.service;

import com.restapi.server.dao.CreditCardDao;
import com.restapi.server.model.CreditCard;
import com.restapi.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardDao creditCardDao;
    private final MemberService memberService;

    @Autowired
    public CreditCardServiceImpl(CreditCardDao creditCardDao, MemberService memberService){
        this.creditCardDao = creditCardDao;
        this.memberService = memberService;
    }


    @Override
    public void addCreditCard(CreditCard creditCard, int id) {
        creditCard.setMemberId(id);
        Member member = memberService.getMemberById(id);
        List<CreditCard> cardList = member.getCreditCardList();
        member.setCreditCardList(cardList);
        cardList.add(creditCard);
        creditCardDao.save(creditCard);

    }

    @Override
    public Iterable<CreditCard> getCreditCards() {
        return creditCardDao.findAll();
    }

    @Override
    public CreditCard getCreditCardByNumber(int number) {
        return creditCardDao.findById(number).get();
    }

    @Override
    public void deleteCreditCardByNumber(int number) {
        creditCardDao.deleteById(number);
    }

    @Override
    public void updateCreditCardByNumber(CreditCard card, int number) {
        CreditCard oldCreditCard = getCreditCardByNumber(number);
        oldCreditCard.setFullName(card.getFullName());
        oldCreditCard.setExpiration_date(card.getExpiration_date());
        oldCreditCard.setCvvNumber(card.getCvvNumber());
        creditCardDao.save(oldCreditCard);
    }

    @Override
    public void deleteAllCreditCards() {
        creditCardDao.deleteAll();
    }


}
