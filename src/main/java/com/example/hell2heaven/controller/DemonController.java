package com.example.hell2heaven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hell2heaven")
public class DemonController {

    @GetMapping
    public String getDemon()
    {

        return "Demon";
    }
}
