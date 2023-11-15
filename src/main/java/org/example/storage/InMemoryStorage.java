package org.example.storage;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStorage {
    private final Map<String, Map<Integer, Object>> storage = new HashMap<>();

    public void addToStorage(String entityType, int entityId, Object entity) {
        if (!storage.containsKey(entityType)) {
            storage.put(entityType, new HashMap<>());
        }
        storage.get(entityType).put(entityId, entity);
    }

    public void updateStorage(String entityType, int entityId, Object updatedEntity) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).put(entityId, updatedEntity);
        }
    }

    public Object getFromStorage(String entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            return storage.get(entityType).get(entityId);
        }
        return null;
    }
    public Map<Integer, Object> getFromStorageAllData(String entityType) {
        return storage.getOrDefault(entityType, Collections.emptyMap());
    }

    public void removeFromStorage(String entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).remove(entityId);
        }
    }
}