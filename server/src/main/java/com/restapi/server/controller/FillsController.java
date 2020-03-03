package com.restapi.server.controller;

import com.restapi.server.service.FillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class FillsController {
    private final FillsService fillsService;

    @Autowired
    public FillsController(FillsService fillsService){ this.fillsService=fillsService;}



}
