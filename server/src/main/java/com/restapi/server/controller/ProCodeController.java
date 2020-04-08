package com.restapi.server.controller;

import com.restapi.server.model.ProCodeTable;
import com.restapi.server.service.ProCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ProCodeController {

    private final ProCodeService proCodeService;

    @Autowired
    public ProCodeController(ProCodeService proCodeService){
        this.proCodeService = proCodeService;
    }

    @RequestMapping(value = "/proCodes",method = RequestMethod.GET)
    public ResponseEntity<Iterable<ProCodeTable>> getCodes() {
        Iterable<ProCodeTable> proCodesList = proCodeService.getProCodes();
        return ResponseEntity.ok(proCodesList);
    }

    @RequestMapping(value = "/proCode/{number}",method = RequestMethod.GET)
    public ResponseEntity<ProCodeTable> getCodesByCode(@PathVariable int number) {
        return ResponseEntity.ok(proCodeService.getProCodeCol(number));
    }

    @RequestMapping(value = "/generateProCode",method = RequestMethod.GET)
    public ResponseEntity<Integer> addProCode(){
        ProCodeTable proCodeTable = new ProCodeTable();
        proCodeService.addProCode(proCodeTable);
        return ResponseEntity.ok(proCodeTable.getProCodeCol());
    }
}

