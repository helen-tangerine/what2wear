package com.example.closet.repository;

import com.example.closet.entity.Clothes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosetRepository extends MongoRepository<Clothes, String> {

    List<Clothes> findAllByBrandAndColor(String brand, String color);

    Clothes findFirstByBrandAndColorAndSize(String brand, String color, int size);
}
