package com.example.hell2heaven.services;

import com.example.hell2heaven.entity.Earthling;
import com.example.hell2heaven.repository.EarthlingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EarthlingSvc {

    private final EarthlingRepository earthlingRepository;

    public EarthlingSvc(EarthlingRepository earthlingRepository) {
        this.earthlingRepository = earthlingRepository;
    }

    // Fetch all Earthlings with role "EARTHLING"
    public List<Earthling> getEarthlingList() {
        return earthlingRepository.findEarthlingByRole("EARTHLING");
    }
}