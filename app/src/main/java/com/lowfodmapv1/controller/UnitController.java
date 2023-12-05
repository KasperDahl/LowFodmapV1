package com.lowfodmapv1.controller;

import com.lowfodmapv1.enums.Unit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller that just returns all the Unit enums values.
 * For now it is kept here but should perhaps be combined with the
 * MealTypeController and get a combined controller/service.
 */
@RestController
@RequestMapping("/api/units")
public class UnitController {

    @GetMapping
    public List<String> getAll() {
        return Stream.of(Unit.values())
                .map(Unit::getValue)
                .collect(Collectors.toList());
    }
}
