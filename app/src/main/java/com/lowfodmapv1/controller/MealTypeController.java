package com.lowfodmapv1.controller;

import com.lowfodmapv1.enums.MealType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

// @RestController
// @RequestMapping("/api/mealtypes")
// public class MealTypeController {

// @GetMapping
// public MealType[] getAll() {
// return MealType.values();
// }
// }
