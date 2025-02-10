package com.example.hell2heaven.controller;

import com.example.hell2heaven.entity.Earthling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.hell2heaven.services.EarthlingSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hell2heaven")
public class EarthlingController {

    private static final Logger log = LoggerFactory.getLogger(EarthlingController.class);

    @Autowired
    private EarthlingSvc earthlingSvc;

    @GetMapping("/demons")
    public List<Earthling> getDemon()
    {
        log.info("returning demons");
        return this.earthlingSvc.getEarthlingList();
    }
}
