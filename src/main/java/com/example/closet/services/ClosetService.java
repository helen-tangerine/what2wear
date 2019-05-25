package com.example.closet.services;

import com.example.closet.entity.Clothes;
import com.example.closet.repository.ClosetRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is responsible for matching the clothes based on numerous factors.
 */
@Service
public class ClosetService {

    private final ClosetRepository repository;
    private final WeatherService weatherService;

    @Autowired
    public ClosetService(ClosetRepository repository, WeatherService weatherService) {
        this.repository = repository;
        this.weatherService = weatherService;
    }

    public JsonObject retrieveJson(String location) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(weatherService.retrieveCurrentWeather(location));

        if(jsonTree.isJsonObject()) {
            return jsonTree.getAsJsonObject();
        } else {
            return null;
        }
    }

    // Retrieves a list of Tops + Bottoms from database based on below;
    // User may adjust these ranges based on their preferences
    public List<Clothes> retrieveClothes(int temperature, String color, int range1, int range2) {

        int[] tempRange = findTemperature(temperature, range1);
        Set<String> colorRange = findColor(color, range2);

        List<Clothes> clothesList = new ArrayList<>();
        for(String colorChosen : colorRange) {
            clothesList.addAll(repository.findAllByTemperatureBetweenAndColor(tempRange[0], tempRange[1], colorChosen));
        }

        // TODO

        return clothesList;
    }

    /*
     * Helper functions
     */

    // Temperature: Max(3, Ideal+/-Current)
    private int[] findTemperature(int temperature, int range) {
        int[] retInt = {temperature+range, temperature-range};
        return retInt;
    }

    // Color: 0 Red, 1 Orange, 2 Yellow, 3 Green, 4 Blue, 5 Violet
    //        99 Black / White (+1 step)
    private Set<String> findColor(String color, int range) {
        Set<String> retSet = new HashSet<>();

        // TODO

        return retSet;
    }
}
