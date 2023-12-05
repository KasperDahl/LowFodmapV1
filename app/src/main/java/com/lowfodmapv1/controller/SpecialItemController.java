package com.lowfodmapv1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowfodmapv1.model.SpecialItem;
import com.lowfodmapv1.service.interfaces.SpecialItemService;

/**
 * This class serves as a REST API controller for special item-related
 * endpoints. It handles HTTP requests and sends responses to the frontend via
 * the base URL endpoint "/api/specialitems".
 */
@RestController
@RequestMapping("/api/specialitems")
public class SpecialItemController {
    private final SpecialItemService specialItemService;

    @Autowired
    public SpecialItemController(SpecialItemService specialItemService) {
        this.specialItemService = specialItemService;
    }

    /**
     * Returns a list of all special items in the database. If there are no special
     * items, it returns a "No Content" status. If an error occurs, it returns an
     * "Internal Server Error" status.
     * 
     * @return List of all special items in the database.
     */
    @GetMapping
    public ResponseEntity<List<SpecialItem>> getAllSpecialItems() {
        try {
            List<SpecialItem> specialItems = specialItemService.getAllSpecialItems();
            if (specialItems.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(specialItems);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new special item in the database. Returns the special item that was
     * created and "Internal Server Error" status if an error occurs.
     * 
     * @param specialItem
     * @return The special item that was created.
     */
    @PostMapping
    public ResponseEntity<SpecialItem> createSpecialItem(@RequestBody SpecialItem specialItem) {
        try {
            return ResponseEntity.ok(specialItemService.createSpecialItem(specialItem));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceImpl-class.
    @GetMapping("/{id}")
    public ResponseEntity<SpecialItem> getSpecialItemById(String id) {
        return specialItemService.getSpecialItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceImpl-class.
    @PutMapping("/{id}")
    public ResponseEntity<SpecialItem> updateSpecialItem(String id, SpecialItem specialItem) {
        return specialItemService.updateSpecialItem(id, specialItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceImpl-class.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialItem(String id) {
        specialItemService.deleteSpecialItem(id);
        return ResponseEntity.noContent().build();
    }

}
