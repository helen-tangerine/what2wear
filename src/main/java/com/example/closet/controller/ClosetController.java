package com.example.closet.controller;

import com.example.closet.entity.Clothes;
import com.example.closet.repository.ClosetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/closet")
public class ClosetController {

    private ClosetRepository repository;

    @Autowired
    public ClosetController(final ClosetRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    @ResponseBody
    public Clothes addClothes(@RequestParam final String brand,
                              @RequestParam final String color,
                              @RequestParam final int size) {
        Clothes shirt = new Clothes(brand, color, size);
        System.out.println("Testing add clothes..." + shirt.toString());
        repository.save(shirt);
        return shirt;
    }

    @GetMapping("/find")
    @ResponseBody
    public List<Clothes> findClothes(@RequestParam final String brand,
                                     @RequestParam final String color) {
        return repository.findAllByBrandAndColor(brand, color);
    }

}
