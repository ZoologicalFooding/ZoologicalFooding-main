package com.restapi.server.service;

import com.restapi.server.model.ProCodeTable;

public interface ProCodeService {
    void addProCode(ProCodeTable code);
    Iterable<ProCodeTable> getProCodes();
    ProCodeTable getProCodeById(int id);
    ProCodeTable getProCodeCol(int proCodeCol);
    int getProCodeMail();
}
