package com.example.hell2heaven.repository;

import com.example.hell2heaven.entity.Earthling;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EarthlingRepository extends MongoRepository<Earthling, String> {
    Optional<Earthling> findByUsername(String username);

    List<Earthling> findEarthlingByRole(String role);
}
