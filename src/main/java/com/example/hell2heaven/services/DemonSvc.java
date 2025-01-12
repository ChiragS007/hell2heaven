package com.example.hell2heaven.services;

import com.example.hell2heaven.entity.Demons;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemonSvc {

    private List<Demons> demonsList = new ArrayList<>();

    public List<Demons> getDemonsList() {
        return this.demonsList;
    }

}
