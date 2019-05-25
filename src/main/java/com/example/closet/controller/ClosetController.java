package com.example.closet.controller;

import com.example.closet.entity.Clothes;
import com.example.closet.repository.ClosetRepository;
import com.example.closet.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    private final ClosetRepository repository;
    private final WeatherService weatherService;

    @Autowired
    public ClosetController(ClosetRepository repository, WeatherService weatherService) {
        this.repository = repository;
        this.weatherService = weatherService;
    }

    /*
     * User Facing
     */

    @PostMapping("/add")
    @ResponseBody
    public Clothes addClothes(@RequestParam final int temperature,
                              @RequestParam final String color) {
        Clothes item = new Clothes(temperature, color);
        System.out.println("Adding to closet: " + item.toString());
        repository.save(item);
        return item;
    }

//    @GetMapping("/wear")
//    @ResponseBody
//    public List<Clothes> chooseClothes(@RequestParam final int temperature,
//                                       @RequestParam final String color) {
//        // TODO
//    }

    /*
     * Non-User Facing
     */

    @GetMapping("/find")
    @ResponseBody
    public List<Clothes> findClothes(@RequestParam final int temperature,
                                     @RequestParam final String color) {
        System.out.println("Finding " + color + " clothes for " + temperature + " degrees");
        return repository.findAllByTemperatureAndColor(temperature, color);
    }

    @DeleteMapping("/remove")
    public void removeClothes(@RequestParam final int temperature,
                              @RequestParam final String color) {
        Clothes item = repository.findFirstByTemperatureAndColor(temperature, color);
        System.out.println("Removing from closet: " + item.toString());
        repository.delete(item);
    }

    @DeleteMapping("/clear")
    public void emptyCloset() {
        System.out.println("Removing all clothes from closet...");
        repository.deleteAll();
    }

    @GetMapping("/weather/{location}")
    @ResponseBody
    public String getWeather(@PathVariable final String location) {
        return weatherService.retrieveCurrentWeather(location);
    }

}
