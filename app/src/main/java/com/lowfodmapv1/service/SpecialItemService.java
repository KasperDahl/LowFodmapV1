package com.lowfodmapv1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.model.SpecialItem;
import com.lowfodmapv1.repository.SpecialItemRepository;

@Service
// This class is used to store special items in the database.
// It is annotated with @Service to indicate that it is a service class.
public class SpecialItemService {
    private final SpecialItemRepository specialItemRepository;

    @Autowired
    public SpecialItemService(SpecialItemRepository specialItemRepository) {
        this.specialItemRepository = specialItemRepository;
    }

    // This method returns all special items.
    public List<SpecialItem> getAllSpecialItems() {
        return specialItemRepository.findAll();
    }

    // This method returns a special item by its id.
    public Optional<SpecialItem> getSpecialItemById(String id) {
        return specialItemRepository.findById(id);
    }

    // This method creates a special item.
    public SpecialItem createSpecialItem(SpecialItem specialItem) {
        return specialItemRepository.save(specialItem);
    }

    // This method updates a special item.
    public Optional<SpecialItem> updateSpecialItem(String id, SpecialItem specialItem) {
        return specialItemRepository.findById(id)
                .map(specialItemData -> {
                    specialItemData.setName(specialItem.getName());
                    specialItemData.setBrand(specialItem.getBrand());
                    specialItemData.setCategory(specialItem.getCategory());
                    specialItemData.setShop(specialItem.getShop());
                    specialItemData.setDescription(specialItem.getDescription());
                    specialItemData.setPrice(specialItem.getPrice());
                    return specialItemRepository.save(specialItemData);
                });

    }

    // This method deletes a special item.
    public void deleteSpecialItem(String id) {
        specialItemRepository.deleteById(id);
    }

}
