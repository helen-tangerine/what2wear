package com.example.closet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clothes {

    @Id
    private String id;

    private String brand;
    private String color;
    private int size;

    public Clothes(String brand, String color, int size) {
        this.brand = brand;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("Clothes[id=%s, brand='%s', color='%s', size='%d']", id, brand, color, size);
    }
}
