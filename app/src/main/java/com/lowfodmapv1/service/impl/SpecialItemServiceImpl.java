package com.lowfodmapv1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.model.SpecialItem;
import com.lowfodmapv1.repository.SpecialItemRepository;
import com.lowfodmapv1.service.interfaces.SpecialItemService;

@Service
// This class implements the SpecialItemService and is used to store special
// items in the database.
public class SpecialItemServiceImpl implements SpecialItemService {
    private final SpecialItemRepository specialItemRepository;

    @Autowired
    public SpecialItemServiceImpl(SpecialItemRepository specialItemRepository) {
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
                    specialItemData.setNote(specialItem.getNote());
                    specialItemData.setPrice(specialItem.getPrice());
                    return specialItemRepository.save(specialItemData);
                });
    }

    // This method deletes a special item.
    public void deleteSpecialItem(String id) {
        specialItemRepository.deleteById(id);
    }

}
