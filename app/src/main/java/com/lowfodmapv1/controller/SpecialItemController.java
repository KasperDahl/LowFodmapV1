package com.lowfodmapv1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowfodmapv1.model.SpecialItem;
import com.lowfodmapv1.service.impl.SpecialItemServiceImpl;
import com.lowfodmapv1.service.interfaces.SpecialItemService;

@RestController
@RequestMapping("/api/specialitems")
// This class is used to store special items in the database.
// It is annotated with @RestController and @RequestMapping to indicate that it
// is a controller class.
public class SpecialItemController {
    private final SpecialItemService specialItemService;

    @Autowired
    public SpecialItemController(SpecialItemServiceImpl specialItemService) {
        this.specialItemService = specialItemService;
    }

    @GetMapping
    // This method returns all special items.
    // It is annotated with @GetMapping to indicate that it is a GET request.
    public ResponseEntity<List<SpecialItem>> getAllSpecialItems() {
        List<SpecialItem> specialItems = specialItemService.getAllSpecialItems();
        if (specialItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(specialItems);
    }

    @GetMapping("/{id}")
    // This method returns a special item by its id.
    // It is annotated with @GetMapping to indicate that it is a GET request.
    public ResponseEntity<SpecialItem> getSpecialItemById(String id) {
        return specialItemService.getSpecialItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    // This method creates a special item.
    // It is annotated with @PostMapping to indicate that it is a POST request.
    public ResponseEntity<SpecialItem> createSpecialItem(@RequestBody SpecialItem specialItem) {
        return ResponseEntity.ok(specialItemService.createSpecialItem(specialItem));
    }

    @PutMapping("/{id}")
    // This method updates a special item.
    // It is annotated with @PutMapping to indicate that it is a PUT request.
    public ResponseEntity<SpecialItem> updateSpecialItem(String id, SpecialItem specialItem) {
        return specialItemService.updateSpecialItem(id, specialItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    // This method deletes a special item.
    // It is annotated with @DeleteMapping to indicate that it is a DELETE request.
    public ResponseEntity<Void> deleteSpecialItem(String id) {
        specialItemService.deleteSpecialItem(id);
        return ResponseEntity.noContent().build();
    }

}
