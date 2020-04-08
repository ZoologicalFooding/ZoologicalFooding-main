package com.restapi.server.service;

import com.restapi.server.model.DiscountCodeTable;

public interface DiscountService {
    void addDisCode(DiscountCodeTable discount);
    Iterable<DiscountCodeTable> getDisCode();
    DiscountCodeTable getDisCodeById(int id);
    DiscountCodeTable getDisCodeCol(int disCodeCol);
    int getDisCodeMail();
}
