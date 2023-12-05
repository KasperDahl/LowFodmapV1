package com.lowfodmapv1.controller;

import com.lowfodmapv1.enums.MealType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller that just returns all the MealType enums values.
 * For now it is kept here but should perhaps be combined with the
 * UnitController and get a combined controller/service.
 */
@RestController
@RequestMapping("/api/mealtypes")
public class MealTypeController {

    @GetMapping
    public List<String> getAll() {
        return Stream.of(MealType.values())
                .map(MealType::getValue)
                .collect(Collectors.toList());
    }
}
