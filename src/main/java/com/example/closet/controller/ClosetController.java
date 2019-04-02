package com.example.closet.controller;

import com.example.closet.entity.Clothes;
import com.example.closet.repository.ClosetRepository;
import com.example.closet.services.ClosetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    private final ClosetRepository repository;
    private final ClosetService closetService;

    @Autowired
    public ClosetController(ClosetRepository repository, ClosetService closetService) {
        this.repository = repository;
        this.closetService = closetService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Clothes addClothes(@RequestParam final String brand,
                              @RequestParam final String color,
                              @RequestParam final int size) {
        Clothes item = new Clothes(brand, color, size);
        System.out.println("Adding to closet: " + item.toString());
        repository.save(item);
        return item;
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Clothes> findClothes(@RequestParam final String brand,
                                     @RequestParam final String color) {
        System.out.println("Finding clothes with " + brand + " and " + color);
        return repository.findAllByBrandAndColor(brand, color);
    }

    @DeleteMapping("/remove")
    public void removeClothes(@RequestParam final String brand,
                              @RequestParam final String color,
                              @RequestParam final int size) {
        Clothes item = repository.findFirstByBrandAndColorAndSize(brand, color, size);
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
        return closetService.retrieveCurrentWeather(location);
    }

}
