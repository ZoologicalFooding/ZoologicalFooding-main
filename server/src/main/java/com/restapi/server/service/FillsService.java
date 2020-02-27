package com.restapi.server.service;

import com.restapi.server.model.Fills;

public interface FillsService {
    void addFills(Fills fills, int containerID, int cardNumber);
    Iterable<Fills> getFills();
    Fills getFillsById(int id);
    void deleteFillsById(int id);
    void updateFillsById(Fills fills, int id);
    void deleteAllFills();

}
