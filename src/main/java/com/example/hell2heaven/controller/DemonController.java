package com.example.hell2heaven.controller;

import com.example.hell2heaven.entity.Demons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.hell2heaven.services.DemonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hell2heaven")
public class DemonController {

    private static final Logger log = LoggerFactory.getLogger(DemonController.class);

    @Autowired
    private DemonSvc demonSvc;

    @GetMapping
    public List<Demons> getDemon()
    {
        log.info("returning demons");
        return this.demonSvc.getDemonsList();
    }
}
