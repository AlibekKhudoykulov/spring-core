package org.example.storage;

import org.example.contant.EntityType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStorage {
    private final Map<EntityType, Map<Integer, Object>> storage = new HashMap<>();

    public void addToStorage(EntityType entityType, int entityId, Object entity) {
        if (!storage.containsKey(entityType)) {
            storage.put(entityType, new HashMap<>());
        }
        storage.get(entityType).put(entityId, entity);
    }

    public void updateStorage(EntityType entityType, int entityId, Object updatedEntity) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).put(entityId, updatedEntity);
        }
    }

    public Object getFromStorage(EntityType entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            return storage.get(entityType).get(entityId);
        }
        return null;
    }
    public Map<Integer, Object> getFromStorageAllData(EntityType entityType) {
        return storage.getOrDefault(entityType, Collections.emptyMap());
    }

    public void removeFromStorage(EntityType entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).remove(entityId);
        }
    }
}