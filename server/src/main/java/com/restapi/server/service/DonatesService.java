package com.restapi.server.service;

import com.restapi.server.model.DonateTable;

public interface DonatesService {
    void addDonates(DonateTable donates);
    Iterable<DonateTable> getDonates();
    DonateTable getDonatesById(int id);
    void deleteDonatesById(int id);
    void updateDonatesById(DonateTable donates, int id);
    void deleteAllDonates();
    void likeDonate(int id);
}
