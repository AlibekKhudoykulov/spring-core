package org.example.storage;

import org.example.constant.EntityType;
import org.example.model.template.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStorage {
    private static final Map<EntityType, Map<Integer, BaseEntity>> storage = new HashMap<>();

    static{
        storage.put(EntityType.USER, new HashMap<>());
        storage.put(EntityType.TRAINEE, new HashMap<>());
        storage.put(EntityType.TRAINING_TYPE, new HashMap<>());
        storage.put(EntityType.TRAINER, new HashMap<>());
        storage.put(EntityType.TRAINING, new HashMap<>());
    }
    public void addToStorage(EntityType entityType, BaseEntity entity) {
        storage.get(entityType).put(entity.getId(), entity);
    }

    public void updateStorage(EntityType entityType, BaseEntity updatedEntity) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).put(updatedEntity.getId(), updatedEntity);
        }
    }

    public BaseEntity getFromStorage(EntityType entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            return storage.get(entityType).get(entityId);
        }
        return null;
    }
    public Map<Integer, BaseEntity> getFromStorageAllData(EntityType entityType) {
        return storage.getOrDefault(entityType, Collections.emptyMap());
    }

    public void removeFromStorage(EntityType entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).remove(entityId);
        }
    }
}