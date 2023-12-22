package org.example.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryStorageTest {

    private InMemoryStorage inMemoryStorage;

    @BeforeEach
    public void setUp() {
        inMemoryStorage = new InMemoryStorage();
    }

    @Test
    public void addToStorageTest() {
        Object object = new Object();
        inMemoryStorage.addToStorage("object", 1, object);
        assertEquals(object, inMemoryStorage.getFromStorage("object", 1));
    }

    @Test
    public void updateStorageTest() {
        Object originalObject = new Object();
        Object updatedObject = new Object();
        inMemoryStorage.addToStorage("object", 1, originalObject);

        inMemoryStorage.updateStorage("object", 1, updatedObject);
        assertEquals(updatedObject, inMemoryStorage.getFromStorage("object", 1));
    }

    @Test
    public void getFromStorageTest() {
        assertNull(inMemoryStorage.getFromStorage("object", 1));
    }

    @Test
    public void removeFromStorageTest() {
        Object object = new Object();
        inMemoryStorage.addToStorage("object", 1, object);
        inMemoryStorage.removeFromStorage("object", 1);

        assertNull(inMemoryStorage.getFromStorage("object", 1));
    }

    @Test
    public void getFromStorageAllDataTest() {
        Object firstObject = new Object();
        Object secondObject = new Object();
        inMemoryStorage.addToStorage("object", 1, firstObject);
        inMemoryStorage.addToStorage("object", 2, secondObject);

        Map<Integer, Object> allData = inMemoryStorage.getFromStorageAllData("object");

        assertEquals(2, allData.size());
        assertTrue(allData.containsKey(1));
        assertTrue(allData.containsKey(2));
        assertEquals(firstObject, allData.get(1));
        assertEquals(secondObject, allData.get(2));
    }
}