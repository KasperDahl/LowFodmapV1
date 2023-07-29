package com.lowfodmapv1.controller;

import com.lowfodmapv1.enums.Unit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
