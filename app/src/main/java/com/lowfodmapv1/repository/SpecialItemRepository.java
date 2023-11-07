package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.SpecialItem;
import org.springframework.data.mongodb.repository.MongoRepository;

// This interface extends MongoRepository and is used to store special items in the database.
public interface SpecialItemRepository extends MongoRepository<SpecialItem, String> {

}
