package org.example.storage;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryStorage {
    private Map<String, Map<Integer, Object>> storage;

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

    public void removeFromStorage(String entityType, int entityId) {
        if (storage.containsKey(entityType)) {
            storage.get(entityType).remove(entityId);
        }
    }

    public Object getById(int entityId) {
        for (Map<Integer, Object> entityMap : storage.values()) {
            if (entityMap.containsKey(entityId)) {
                return entityMap.get(entityId);
            }
        }
        return null;
    }
}