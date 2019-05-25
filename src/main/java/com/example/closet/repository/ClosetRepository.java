package com.example.closet.repository;

import com.example.closet.entity.Clothes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosetRepository extends MongoRepository<Clothes, String> {

    List<Clothes> findAllByTemperatureAndColor(int temperature, String color);

    List<Clothes> findAllByTemperatureBetweenAndColor(int tempStart, int tempEnd, String color);

    Clothes findFirstByTemperatureAndColor(int temperature , String color);
}
