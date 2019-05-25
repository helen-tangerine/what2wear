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

    private int temperature;
    private String color;

    public Clothes(int temperature, String color) {
        this.temperature = temperature;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("Clothes[ideal_temp='%d', color='%s']", temperature, color);
    }
}
