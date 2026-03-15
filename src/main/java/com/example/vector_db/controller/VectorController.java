package com.example.vector_db.controller;




import com.example.vector_db.engine.VectorSearchEngine;
import com.example.vector_db.engine.VectorStore;
import com.example.vector_db.model.SearchRecord;
import com.example.vector_db.model.SearchResult;
import com.example.vector_db.model.VectorRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vectors")
public class VectorController {

    private final VectorStore store;
    private final VectorSearchEngine searchEngine;

    public VectorController(VectorStore store, VectorSearchEngine searchEngine) {
        this.store = store;
        this.searchEngine = searchEngine;
    }

    @PostMapping
    public void insert(@RequestBody VectorRecord record) {
        store.insert(record);
    }

    @GetMapping("/{id}")
    public VectorRecord get(@PathVariable String id) {
        return store.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        store.delete(id);
    }

    @PostMapping("/search")
    public List<SearchResult> search(
            @RequestBody Map<String, Object> request) {

        List<Double> vector = (List<Double>) request.get("vector");
        int topK = (int) request.get("topK");

        return searchEngine.search(vector, topK);
    }
}
