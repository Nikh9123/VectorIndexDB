package com.example.vector_db.engine;


import com.example.vector_db.model.VectorRecord;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class VectorStore {

    private final Map<String, VectorRecord> store = new HashMap<>();

    public void insert(VectorRecord record) {
        store.put(record.getId(), record);
    }

    public VectorRecord get(String id) {
        return store.get(id);
    }

    public void update(String id, VectorRecord record) {
        store.put(id, record);
    }

    public void delete(String id) {
        store.remove(id);
    }

    public Collection<VectorRecord> getAll() {
        return store.values();
    }
}