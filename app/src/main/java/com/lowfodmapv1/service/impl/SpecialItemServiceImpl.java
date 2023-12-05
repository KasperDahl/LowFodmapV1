package com.lowfodmapv1.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.model.SpecialItem;
import com.lowfodmapv1.repository.SpecialItemRepository;
import com.lowfodmapv1.service.interfaces.SpecialItemService;

/**
 * This class implements the SpecialItemService and is used to store special
 * items in the database.
 */
@Service
public class SpecialItemServiceImpl implements SpecialItemService {
    private final SpecialItemRepository specialItemRepository;

    @Autowired
    public SpecialItemServiceImpl(SpecialItemRepository specialItemRepository) {
        this.specialItemRepository = specialItemRepository;
    }

    // This method returns all special items.
    /**
     * Returns the sorted list of all special items.
     * 
     * @return List of all special items.
     */
    public List<SpecialItem> getAllSpecialItems() {
        List<SpecialItem> allSpecialItems = specialItemRepository.findAll();
        return sortSpecialItemsByName(allSpecialItems);
    }

    // This method creates a special item.
    /**
     * Creates a new special item.
     * 
     * @param specialItem The special item to create.
     * @return The created special item.
     */
    public SpecialItem createSpecialItem(SpecialItem specialItem) {
        return specialItemRepository.save(specialItem);
    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceController-class.
    public Optional<SpecialItem> getSpecialItemById(String id) {
        return specialItemRepository.findById(id);
    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceController-class.
    public Optional<SpecialItem> updateSpecialItem(String id, SpecialItem specialItem) {
        return specialItemRepository.findById(id)
                .map(specialItemData -> {
                    specialItemData.setName(specialItem.getName());
                    specialItemData.setBrand(specialItem.getBrand());
                    specialItemData.setCategory(specialItem.getCategory());
                    specialItemData.setShop(specialItem.getShop());
                    specialItemData.setNote(specialItem.getNote());
                    specialItemData.setPrice(specialItem.getPrice());
                    return specialItemRepository.save(specialItemData);
                });
    }

    // This method has not been tested yet, neither has the method in the
    // SpecialItemServiceController-class.
    public void deleteSpecialItem(String id) {
        specialItemRepository.deleteById(id);
    }

    /**
     * Sorts special items by name.
     * OBS! Maybe move this method closer to where the data is used (Vue.js)
     * 
     * @param specialItems
     * @return List of sorted special items.
     */
    public List<SpecialItem> sortSpecialItemsByName(List<SpecialItem> specialItems) {
        Collections.sort(specialItems, new Comparator<SpecialItem>() {
            @Override
            public int compare(SpecialItem s1, SpecialItem s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        return specialItems;
    }
}
