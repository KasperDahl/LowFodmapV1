package com.lowfodmapv1.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.lowfodmapv1.model.SpecialItem;

public interface SpecialItemService {
    List<SpecialItem> getAllSpecialItems();
    Optional<SpecialItem> getSpecialItemById(String id);
    SpecialItem createSpecialItem(SpecialItem specialItem);
    Optional<SpecialItem> updateSpecialItem(String id, SpecialItem specialItem);
    void deleteSpecialItem(String id);
}
