package com.restapi.server.service;

import com.restapi.server.model.Donate;

public interface DonateService {
    void addDonates(Donate donate);
    Iterable<Donate> getDonates();
    Donate getDonatesById(int id);
    void deleteDonateById(int id);
    void updateDonateById(Donate donates, int id);
    void deleteAllDonates();


}
