package com.example.hell2heaven.services;

import com.example.hell2heaven.entity.Earthling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EarthlingSvc {

    private List<Earthling> earthlingList = new ArrayList<>();

    public List<Earthling> getEarthlingList() {
        return this.earthlingList;
    }

}
