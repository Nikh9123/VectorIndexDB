package com.example.vector_db.service;

import com.example.vector_db.engine.VectorSearchEngine;
import com.example.vector_db.model.SearchRequest;
import com.example.vector_db.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final VectorSearchEngine searchEngine;

    public SearchService(VectorSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public List<SearchResult> search(SearchRequest request) {

        return searchEngine.search(
                request.getVector(),
                request.getTopK()
        );
    }
}
